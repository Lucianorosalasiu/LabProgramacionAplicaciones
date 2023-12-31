package persistencia;

import Enums.EstadoActividad;
import dataTypes.DTActividadTuristica;
import dataTypes.DTBusqueda;
import dataTypes.DTCategoria;
import dataTypes.DTCompraPaquete;
import dataTypes.DTDepartamento;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTProveedor;
import dataTypes.DTSalidaTuristica;
import dataTypes.DTTop;
import dataTypes.DTTurista;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import exceptions.MyException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import persistencia.entidades.EActividadTuristica;
import persistencia.entidades.ECategoria;
import persistencia.entidades.ECompraPaquete;
import persistencia.entidades.EDepartamento;
import persistencia.entidades.EInscripcion;
import persistencia.entidades.EPaqueteActividadTuristica;
import persistencia.entidades.EProveedor;
import persistencia.entidades.ESalidaTuristica;
import persistencia.entidades.ESeguidores;
import persistencia.entidades.ETurista;
import persistencia.entidades.EUsuario;

/**
 *
 * @author all
 */
public class DataPersistencia implements IDataPersistencia {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("puConexionBD");
    private static DataPersistencia instancia = null;

    private DataPersistencia() {
    }

    public static DataPersistencia getInstance() {
        if (instancia == null) {
            instancia = new DataPersistencia();
        }
        return instancia;
    }

    /**
     *
     * @param email del usuario, ya sea proveedor o turista
     * @param nickname lo mismo que el email
     * @throws MyException en caso de que exista un usuario que coincida con el
     * nick o el email recibidos
     */
    @Override
    public void existeUsuario(String email, String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();
        String qryProveedor = "SELECT p from EProveedor p where p.email = :emailProveedor OR p.nickname = :nicknameProveedor";
        List<EProveedor> resultadoProveedor = new LinkedList<>();

        String qryTurista = "SELECT t from ETurista t where t.email = :emailTurista OR t.nickname = :nicknameTurista";
        List<ETurista> resultadoTurista = new LinkedList<>();

        try {
            resultadoProveedor = em.createQuery(qryProveedor, EProveedor.class)
                    .setParameter("emailProveedor", email)
                    .setParameter("nicknameProveedor", nickname)
                    .getResultList();

            if (resultadoProveedor.isEmpty()) {
                resultadoTurista = em.createQuery(qryTurista, ETurista.class)
                        .setParameter("emailTurista", email)
                        .setParameter("nicknameTurista", nickname)
                        .getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("¡ERROR! Algo salio mal al realizar la consulta");
        } finally {
            em.close();
        }

        if (!resultadoProveedor.isEmpty() || !resultadoTurista.isEmpty()) {
            throw new MyException("Ya existe un usuario con el email o nickname ingresado.");
        }
    }

    @Override
    public ETurista buscarTuristaPorNickname(String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
            TypedQuery<ETurista> touristResult = em.createQuery(query, ETurista.class);
            touristResult.setParameter("nickname", nickname);
            return touristResult.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("No se encontró el turista con el nickname brindado");
        } finally {
            em.close();
        }
    }

    @Override
    public EProveedor buscarProveedorPorNickname(String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT p FROM EProveedor p WHERE p.nickname = :nickname";
            TypedQuery<EProveedor> providerResult = em.createQuery(query, EProveedor.class);
            providerResult.setParameter("nickname", nickname);
            return providerResult.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("No se encontró el proveedor con el nickname brindado");
        } finally {
            em.close();
        }
    }

    @Override
    public DTTurista obtenerTurista(long idTurista) {
        EntityManager em = emf.createEntityManager();

        try {

            ETurista eTurista = em.find(ETurista.class, idTurista);
            DTTurista dtTurista = new DTTurista(eTurista.getId(), eTurista.getNickname(), eTurista.getEmail(), eTurista.getNacionality());
            return dtTurista;

        } catch (Exception e) {
            DTTurista dtTurista = new DTTurista();
            return dtTurista;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTTurista> obtenerTuristas() {
        EntityManager em = emf.createEntityManager();
        List<DTTurista> touristList = new LinkedList<>();
        List<ETurista> qryResults = new LinkedList<>();

        try {
            String query = "select t from ETurista t";
            qryResults = em.createQuery(query, ETurista.class).getResultList();

            for (ETurista t : qryResults) {
                DTTurista turista = new DTTurista(
                        t.getId(),
                        t.getNickname(),
                        t.getName(),
                        t.getLastName(),
                        t.getEmail(),
                        t.getBirthDate(),
                        t.getPassword(),
                        t.getImagePath(),
                        t.getPhoto(),
                        t.getNacionality()
                );
                touristList.add(turista);
            }

            return touristList;
        } catch (Exception e) {
            return touristList;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTProveedor> obtenerProveedores() {
        EntityManager em = emf.createEntityManager();
        List<DTProveedor> resultados = new LinkedList<>();
        List<EProveedor> qryResult = new LinkedList<>();

        try {
            String query = "select e from EProveedor e";
            qryResult = em.createQuery(query, EProveedor.class).getResultList();

            for (EProveedor p : qryResult) {
                DTProveedor dtProveedor = new DTProveedor(
                        p.getId(),
                        p.getNickname(),
                        p.getName(),
                        p.getLastName(),
                        p.getEmail(),
                        p.getBirthDate(),
                        p.getPassword(),
                        p.getImagePath(),
                        p.getPhoto(),
                        p.getDescription(),
                        p.getWebsiteURL()
                );
                resultados.add(dtProveedor);
            }

            return resultados;
        } catch (Exception e) {
            return resultados;
        } finally {
            em.close();
        }
    }

    @Override
    public void altaProveedor(DTProveedor proveedor) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            EProveedor nProveedor = new EProveedor(
                    proveedor.getNickname(),
                    proveedor.getName(),
                    proveedor.getLastName(),
                    proveedor.getEmail(),
                    proveedor.getBirthDate(),
                    proveedor.getPassword(),
                    proveedor.getImagePath(),
                    proveedor.getPhoto(),
                    proveedor.getDescription(),
                    proveedor.getWebsiteURL()
            );

            em.getTransaction().begin();
            em.persist(nProveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("¡ERROR! Algo salio mal al insertar el nuevo proveedor");
        } finally {
            em.close();
        }
    }

    @Override
    public void altaTurista(DTTurista turista) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            ETurista nTurista = new ETurista(
                    turista.getNickname(),
                    turista.getName(),
                    turista.getLastName(),
                    turista.getEmail(),
                    turista.getBirthDate(),
                    turista.getPassword(),
                    turista.getImagePath(),
                    turista.getPhoto(),
                    turista.getNacionality()
            );

            em.getTransaction().begin();
            em.persist(nTurista);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("¡ERROR! Algo salio mal al insertar el nuevo turista");
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarProveedor(DTProveedor proveedor) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            // Se busca al EProveedor por su nickname
            String nickname = proveedor.getNickname();
            EProveedor proveedorAActualizar = buscarProveedorPorNickname(nickname);

            em.getTransaction().begin();

            // Se intenta actualizar los campos del ETurista
            proveedorAActualizar.setName(proveedor.getName());
            proveedorAActualizar.setLastName(proveedor.getLastName());
            proveedorAActualizar.setBirthDate(proveedor.getBirthDate());
            proveedorAActualizar.setDescription(proveedor.getDescription());
            proveedorAActualizar.setWebsiteURL(proveedor.getWebsiteURL());
            if (proveedor.getPhoto() != null) {
                proveedorAActualizar.setPhoto(proveedor.getPhoto());
            }
            if ((proveedor.getPassword() != null) && !proveedor.getPassword().isBlank()) {
                proveedorAActualizar.setPassword(proveedor.getPassword());
            }

            // Se intenta realizar la transacción de actualización
            em.merge(proveedorAActualizar);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("ERROR! Algo salio mal al intentar actualizar el proveedor");
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarTurista(DTTurista turista) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            // Se busca al ETurista por su nickname
            String nickname = turista.getNickname();
            ETurista turistaAActualizar = buscarTuristaPorNickname(nickname);

            em.getTransaction().begin();

            // Se intenta actualizar los campos del ETurista
            turistaAActualizar.setName(turista.getName());
            turistaAActualizar.setLastName(turista.getLastName());
            turistaAActualizar.setBirthDate(turista.getBirthDate());
            turistaAActualizar.setNacionality(turista.getNacionality());

            if (turista.getPhoto() != null) {
                turistaAActualizar.setPhoto(turista.getPhoto());
            }
            if ((turista.getPassword() != null) && !turista.getPassword().isBlank()) {
                turistaAActualizar.setPassword(turista.getPassword());
            }

            // Se intenta realizar la transacción de actualización
            em.merge(turistaAActualizar);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(Arrays.toString(e.getStackTrace()) + e.getMessage());
            throw new MyException("ERROR! Algo salio mal al intentar actualizar el turista");
        } finally {
            em.close();
        }
    }

    @Override
    public void seguirUsuario(long idSeguidor, long idSeguido) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Obtener los usuarios involucrados en el seguimiento
            EUsuario seguidor = em.find(EUsuario.class, idSeguidor);
            EUsuario seguido = em.find(EUsuario.class, idSeguido);

            // Verificar si ambos usuarios existen
            if (seguidor == null) {
                throw new MyException("¡ERROR! No se encontró el usuario seguidor.");
            }

            if (seguido == null) {
                throw new MyException("¡ERROR! No se encontró el usuario a seguir.");
            }

            // Se crea la instancia de ESeguidores con los IDs
            ESeguidores relacion = new ESeguidores();
            relacion.setSeguidorId(seguidor.getId());
            relacion.setSeguidoId(seguido.getId());

            em.persist(relacion);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("¡ERROR! Algo salió mal al seguir al usuario.");
        } finally {
            em.close();
        }
    }

    @Override
    public void dejarDeSeguirUsuario(long idSeguidor, long idSeguido) throws MyException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Verificar si ambos usuarios existen
            EUsuario seguidor = em.find(EUsuario.class, idSeguidor);
            EUsuario seguido = em.find(EUsuario.class, idSeguido);

            if (seguidor == null) {
                throw new MyException("¡ERROR! No se encontró el usuario seguidor.");
            }

            if (seguido == null) {
                throw new MyException("¡ERROR! No se encontró el usuario a dejar de seguir.");
            }

            // Buscar y eliminar la relación de seguimiento
            ESeguidores relacion = buscarRelacionDeSeguimiento(idSeguidor, idSeguido, em);
            if (relacion != null) {
                em.remove(relacion);

                // Remover IDs de las listas en lugar de instancias
                // seguido.removerSeguidor(idSeguidor);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(DataPersistencia.class.getName()).log(Level.SEVERE, null, e);
            throw new MyException("¡ERROR! Algo salió mal al dejar de seguir al usuario.");
        } finally {
            em.close();
        }
    }
    
    @Override
    public ArrayList<Long> obtenerSeguidos(long idSeguidor){
        EntityManager em = emf.createEntityManager();
        ArrayList<Long> seguidos = new ArrayList<>();

        try {
            String querySeguidos = "SELECT r FROM ESeguidores r WHERE r.seguidorId = :seguidorId";
            List<ESeguidores> eSeguidosList = em.createQuery(querySeguidos, ESeguidores.class)
                    .setParameter("seguidorId", idSeguidor)
                    .getResultList();

            for (ESeguidores r : eSeguidosList) {
                seguidos.add(r.getSeguidoId());
            }

            return seguidos;

        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Long> obtenerSeguidores(long idSeguido){
        EntityManager em = emf.createEntityManager();
        ArrayList<Long> seguidores = new ArrayList<>();

        try {
            String querySeguidores = "SELECT r FROM ESeguidores r WHERE r.seguidoId = :seguidoId";
            List<ESeguidores> eSeguidoresList = em.createQuery(querySeguidores, ESeguidores.class)
                    .setParameter("seguidoId", idSeguido)
                    .getResultList();

            for (ESeguidores r : eSeguidoresList) {
                seguidores.add(r.getSeguidorId());
            }

            return seguidores;

        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
    
    private ESeguidores buscarRelacionDeSeguimiento(long idSeguidor, long idSeguido, EntityManager em) {
        try {
            return em.createQuery(
                    "SELECT r FROM ESeguidores r WHERE r.seguidorId = :seguidorId AND r.seguidoId = :seguidoId",
                    ESeguidores.class)
                    .setParameter("seguidorId", idSeguidor)
                    .setParameter("seguidoId", idSeguido)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró la relación de seguimiento
        }
    }

    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeTurista(long idTurista) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> listaESalidas = new LinkedList<>();
        List<DTSalidaTuristica> listaSalidas = new LinkedList<>();
        List<EInscripcion> listaEInscripciones = new LinkedList<>();

        try {
            // Se busca el turista que coincida con la id recibida
            ETurista turista = em.find(ETurista.class, idTurista);

            // Se obtiene su lista de inscripciones
            listaEInscripciones = turista.getEInscripciones();

            // Por cada inscripción se obtiene su salida turistica asociada
            for (EInscripcion i : listaEInscripciones) {
                listaESalidas.add(i.getESalidaTuristica());
            }

            for (ESalidaTuristica s : listaESalidas) {
                DTSalidaTuristica salida = new DTSalidaTuristica(
                        s.getNombre(),
                        s.getCantidadMaxTuristas(),
                        s.getFechaSalida(),
                        s.getLugar(),
                        s.getFechaAlta(),
                        s.getImagen()
                );

                listaSalidas.add(salida);
            }
            return listaSalidas;
        } catch (Exception e) {
            return listaSalidas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeProveedor(long idProveedor) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> listaESalidas = new LinkedList<>();
        List<DTSalidaTuristica> listaSalidas = new LinkedList<>();
        List<EActividadTuristica> listaEActividades = new LinkedList<>();

        try {
            // Se busca el proveedor que coincida con la id recibida
            EProveedor proveedor = em.find(EProveedor.class, idProveedor);

            // Se obtiene su lista de de actividades
            listaEActividades = proveedor.getActividades();

            // Por cada actividad se obtiene su salida turistica asociada
            for (EActividadTuristica a : listaEActividades) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    listaESalidas.addAll(a.getESalidasTuristicas());
                }
            }

            for (ESalidaTuristica s : listaESalidas) {
                DTSalidaTuristica salida = new DTSalidaTuristica(
                        s.getNombre(),
                        s.getCantidadMaxTuristas(),
                        s.getFechaSalida(),
                        s.getLugar(),
                        s.getFechaAlta()
                );

                listaSalidas.add(salida);
            }
            return listaSalidas;
        } catch (Exception e) {
            return listaSalidas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTSalidaTuristica> obtenerSalidasDeProveedorCompleto(long idProveedor) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> listaESalidas = new LinkedList<>();
        List<DTSalidaTuristica> listaSalidas = new LinkedList<>();
        List<EActividadTuristica> listaEActividades = new LinkedList<>();

        try {
            // Se busca el proveedor que coincida con la id recibida
            EProveedor proveedor = em.find(EProveedor.class, idProveedor);

            // Se obtiene su lista de de actividades
            listaEActividades = proveedor.getActividades();

            // Por cada actividad se obtiene su salida turistica asociada
            for (EActividadTuristica a : listaEActividades) {
                listaESalidas.addAll(a.getESalidasTuristicas());
            }

            for (ESalidaTuristica s : listaESalidas) {
                DTSalidaTuristica salida = new DTSalidaTuristica(
                        s.getNombre(),
                        s.getCantidadMaxTuristas(),
                        s.getFechaSalida(),
                        s.getLugar(),
                        s.getFechaAlta()
                );

                listaSalidas.add(salida);
            }
            return listaSalidas;
        } catch (Exception e) {
            return listaSalidas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesDeProveedor(long idProveedor) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> listaEActividades = new LinkedList<>();
        List<DTActividadTuristica> listaActividades = new LinkedList<>();

        try {
            // Se busca el proveedor que coincida con la id recibida
            EProveedor proveedor = em.find(EProveedor.class, idProveedor);

            // Se obtiene su lista de de actividades
            listaEActividades = proveedor.getActividades();

            for (EActividadTuristica a : listaEActividades) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(
                            a.getId(),
                            a.getNombre(),
                            a.getDescripcion(),
                            a.getDuracion(),
                            a.getCosto(),
                            a.getCiudad(),
                            a.getFechaAlta()
                    );
                    listaActividades.add(dtActividadTuristica);
                }
            }
            return listaActividades;
        } catch (Exception e) {
            return listaActividades;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTActividadTuristica> obtenerActividadesFinalizables(long idProveedor) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> listaEActividades = new LinkedList<>();
        List<DTActividadTuristica> listaActividades = new LinkedList<>();

        try {
            // Se busca el proveedor que coincida con la id recibida
            EProveedor proveedor = em.find(EProveedor.class, idProveedor);

            // Se obtiene su lista de de actividades
            listaEActividades = proveedor.getActividades();

            for (EActividadTuristica a : listaEActividades) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA && obtenerSalidasTuristicasVigentes(a.getNombre()).size() == 0) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(
                            a.getId(),
                            a.getNombre(),
                            a.getDescripcion(),
                            a.getDuracion(),
                            a.getCosto(),
                            a.getCiudad(),
                            a.getFechaAlta()
                    );
                    listaActividades.add(dtActividadTuristica);
                }
            }
            ArrayList<DTActividadTuristica> resultado = new ArrayList<>(listaActividades);
            return resultado;
        } catch (Exception e) {
            ArrayList<DTActividadTuristica> resultado = new ArrayList<>(listaActividades);
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesDeProveedorCompleto(long idProveedor) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> listaEActividades = new LinkedList<>();
        List<DTActividadTuristica> listaActividades = new LinkedList<>();

        try {
            // Se busca el proveedor que coincida con la id recibida
            EProveedor proveedor = em.find(EProveedor.class, idProveedor);

            // Se obtiene su lista de de actividades
            listaEActividades = proveedor.getActividades();

            for (EActividadTuristica a : listaEActividades) {
                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(
                        a.getId(),
                        a.getNombre(),
                        a.getDescripcion(),
                        a.getDuracion(),
                        a.getCosto(),
                        a.getCiudad(),
                        a.getFechaAlta()
                );
                listaActividades.add(dtActividadTuristica);
            }
            return listaActividades;
        } catch (Exception e) {
            return listaActividades;
        } finally {
            em.close();
        }
    }

    @Override
    public void existeActividadTuristica(String nombre) throws MyException {
        EntityManager em = emf.createEntityManager();
        String consulta = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
        List<EActividadTuristica> resultado = new LinkedList<>();

        try {
            resultado = em.createQuery(consulta, EActividadTuristica.class)
                    .setParameter("nombreActividad", nombre)
                    .getResultList();
        } catch (Exception e) {
            throw new MyException("Algo salio mal consultando la base de datos. ");
        } finally {
            em.close();
        }

        if (!resultado.isEmpty()) {
            throw new MyException("Ya existe una actividad turistica con ese nombre. ");
        }
    }

    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto) {
        EntityManager em = emf.createEntityManager();

        EDepartamento eDepartamento = em.find(EDepartamento.class, idDepartamento);

        List<ECategoria> eCategorias = new LinkedList<>();

        for (Long l : categorias) {
            eCategorias.add(em.find(ECategoria.class, l));
        }

        EActividadTuristica nuevaActividad = new EActividadTuristica(dtActividadTuristica.getNombre(),
                dtActividadTuristica.getDescripcion(), dtActividadTuristica.getDuracion(),
                dtActividadTuristica.getCosto(), dtActividadTuristica.getCiudad(),
                dtActividadTuristica.getFechaAlta(), eDepartamento, eCategorias, foto, "");

        /*vinculo la categoria con las actividades que la tienen*/
        for (ECategoria e : eCategorias) {
            e.addActividad(nuevaActividad);
        }

        try {
            em.getTransaction().begin();
            /*consigo el proveedor al que le voy a agregar esta actividad a su lista*/
            EProveedor eProveedor = em.find(EProveedor.class, idProveedor);
            /*le paso la actividad recien creada al proveedor para que la agregue a su lista*/
            eProveedor.addActividad(nuevaActividad);
            /*finalmente persisto la nueva actividad*/
            em.persist(nuevaActividad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void altaActividadTuristica(DTActividadTuristica dtActividadTuristica, Long idDepartamento, Long idProveedor, List<Long> categorias, byte[] foto, String url) {
        EntityManager em = emf.createEntityManager();

        EDepartamento eDepartamento = em.find(EDepartamento.class, idDepartamento);

        List<ECategoria> eCategorias = new LinkedList<>();

        for (Long l : categorias) {
            eCategorias.add(em.find(ECategoria.class, l));
        }

        if (url == null) {
            url = "";
        }

        EActividadTuristica nuevaActividad = new EActividadTuristica(dtActividadTuristica.getNombre(),
                dtActividadTuristica.getDescripcion(), dtActividadTuristica.getDuracion(),
                dtActividadTuristica.getCosto(), dtActividadTuristica.getCiudad(),
                dtActividadTuristica.getFechaAlta(), eDepartamento, eCategorias, foto, url);

        /*vinculo la categoria con las actividades que la tienen*/
        for (ECategoria e : eCategorias) {
            e.addActividad(nuevaActividad);
        }

        try {
            em.getTransaction().begin();
            /*consigo el proveedor al que le voy a agregar esta actividad a su lista*/
            EProveedor eProveedor = em.find(EProveedor.class, idProveedor);
            /*le paso la actividad recien creada al proveedor para que la agregue a su lista*/
            eProveedor.addActividad(nuevaActividad);
            /*finalmente persisto la nueva actividad*/
            em.persist(nuevaActividad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public byte[] obtenerFotoActividadTuristica(Long id) {
        EntityManager em = emf.createEntityManager();
        byte[] foto = null;
        try {
            em.getTransaction().begin();

            EActividadTuristica actividad = em.find(EActividadTuristica.class, id);

            if (actividad != null) {
                foto = actividad.getFoto();
            }
            em.getTransaction().commit();
            return foto;
        } finally {
            em.close();
        }
    }

    @Override
    public String obtenerUrlVideo(Long id) {
        EntityManager em = emf.createEntityManager();
        String url = "";
        try {
            em.getTransaction().begin();

            EActividadTuristica actividad = em.find(EActividadTuristica.class, id);

            if (actividad != null) {
                url = actividad.getUrl();
            }
            em.getTransaction().commit();
            return url;
        } catch (Exception e) {
            return url;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicas(Long idDepartamento) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List<DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();

        try {
            String consulta = "select a from EActividadTuristica a where a.eDepartamento.id = :idDepartamento";
            resultados = em.createQuery(consulta, EActividadTuristica.class)
                    .setParameter("idDepartamento", idDepartamento).getResultList();

            for (EActividadTuristica a : resultados) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(
                            a.getId(),
                            a.getNombre(),
                            a.getDescripcion(),
                            a.getDuracion(),
                            a.getCosto(),
                            a.getCiudad(),
                            a.getFechaAlta());

                    dtActividadesTuristicas.add(dtActividadTuristica);
                }
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public DTActividadTuristica obtenerActividadTuristicaNull(Long idActividad) {
        EntityManager em = emf.createEntityManager();
        String categoriasString = "";
        try {
            em.getTransaction().begin();
            EActividadTuristica eActividadTuristica = em.find(EActividadTuristica.class, idActividad);
            if (eActividadTuristica.getEstadoActividad() == EstadoActividad.CONFIRMADA) {

                for (ECategoria e : eActividadTuristica.getCategorias()) {
                    categoriasString += "|" + e.getNombre();
                }

                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(eActividadTuristica.getId(), eActividadTuristica.getNombre(),
                        eActividadTuristica.getDescripcion(), eActividadTuristica.getDuracion(),
                        eActividadTuristica.getCosto(), eActividadTuristica.getCiudad(), eActividadTuristica.getFechaAlta(),
                        categoriasString, eActividadTuristica.getCantidadVistas(), eActividadTuristica.getUrl());

                //si consulte por esta actividad es porque la quiero visualizar por lo que le sumo +1 a la cantidad de vistas;
                int vistasActuales = eActividadTuristica.getCantidadVistas();
                vistasActuales += 1;
                eActividadTuristica.setCantidadVistas(vistasActuales);
                em.getTransaction().commit();
                return dtActividadTuristica;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicasConId(Long idDepartamento) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List<DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();

        try {
            String consulta = "select a from EActividadTuristica a where a.eDepartamento.id = :idDepartamento";
            resultados = em.createQuery(consulta, EActividadTuristica.class)
                    .setParameter("idDepartamento", idDepartamento).getResultList();

            for (EActividadTuristica a : resultados) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(a.getId(), a.getNombre(), a.getDescripcion(),
                            a.getDuracion(), a.getCosto(), a.getCiudad(), a.getFechaAlta());

                    dtActividadesTuristicas.add(dtActividadTuristica);
                }
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicasPorCategoria(String nombreCategoria) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List<DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();

        try {
            String consulta = "select a from EActividadTuristica a";
            resultados = em.createQuery(consulta, EActividadTuristica.class).getResultList();
            //consigo todas las actividades turisticas
            for (EActividadTuristica a : resultados) {
                //filtro para quedarme solo con las confirmadas
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    //recorro sus categorias para saber si alguna de ellas coincide con la que estoy buscando
                    for (ECategoria c : a.getCategorias()) {
                        //de ser asi la devuelvo
                        if (c.getNombre().equals(nombreCategoria)) {
                            DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(a.getId(), a.getNombre(), a.getDescripcion(),
                                    a.getDuracion(), a.getCosto(), a.getCiudad(), a.getFechaAlta());

                            dtActividadesTuristicas.add(dtActividadTuristica);
                        }
                    }

                }
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesTuristicas() {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados = new LinkedList<>();
        List<DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();

        try {
            String consulta = "select a from EActividadTuristica a ";
            resultados = em.createQuery(consulta, EActividadTuristica.class).getResultList();

            for (EActividadTuristica a : resultados) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(a.getId(), a.getNombre(), a.getDescripcion(),
                            a.getDuracion(), a.getCosto(), a.getCiudad(), a.getFechaAlta());

                    dtActividadesTuristicas.add(dtActividadTuristica);
                }
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public DTActividadTuristica obtenerActividadTuristica(Long idActividad) {
        EntityManager em = emf.createEntityManager();
        String categoriasString = "";
        try {
            EActividadTuristica eActividadTuristica = em.find(EActividadTuristica.class, idActividad);
            if (eActividadTuristica.getEstadoActividad() == EstadoActividad.CONFIRMADA) {

                for (ECategoria e : eActividadTuristica.getCategorias()) {
                    categoriasString += "|" + e.getNombre();
                }

                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(eActividadTuristica.getNombre(),
                        eActividadTuristica.getDescripcion(), eActividadTuristica.getDuracion(),
                        eActividadTuristica.getCosto(), eActividadTuristica.getCiudad(), eActividadTuristica.getFechaAlta(),
                        categoriasString);
                return dtActividadTuristica;
            } else {
                DTActividadTuristica dtActividadTuristica = new DTActividadTuristica();
                return dtActividadTuristica;
            }
        } catch (Exception e) {
            DTActividadTuristica dtActividadTuristica = new DTActividadTuristica();
            return dtActividadTuristica;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionados(Long idActividad) {
        EntityManager em = emf.createEntityManager();
        List<EPaqueteActividadTuristica> resultados_consulta = new LinkedList<>();
        List<DTPaqueteActividadTuristica> resultados = new LinkedList<>();

        try {
            EActividadTuristica actividad = em.find(EActividadTuristica.class, idActividad);

            if (actividad.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                resultados_consulta = actividad.getPaquetes();

                for (EPaqueteActividadTuristica p : resultados_consulta) {
                    DTPaqueteActividadTuristica dtPaqueteActividadTuristica = new DTPaqueteActividadTuristica(p.getNombre());
                    resultados.add(dtPaqueteActividadTuristica);
                }
            }
            return resultados;
        } catch (Exception e) {
            return resultados;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesRelacionadosCompletos(Long idActividad) {
        EntityManager em = emf.createEntityManager();
        List<EPaqueteActividadTuristica> resultados_consulta = new LinkedList<>();
        List<DTPaqueteActividadTuristica> resultados = new LinkedList<>();

        try {
            EActividadTuristica actividad = em.find(EActividadTuristica.class, idActividad);

            if (actividad.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                resultados_consulta = actividad.getPaquetes();

                for (EPaqueteActividadTuristica p : resultados_consulta) {
                    DTPaqueteActividadTuristica dtPaqueteActividadTuristica = new DTPaqueteActividadTuristica(p.getNombre(), p.getDescripcion(), p.getValidez(), p.getDescuento(), p.getFechaAlta());
                    resultados.add(dtPaqueteActividadTuristica);
                }
            }
            return resultados;
        } catch (Exception e) {
            return resultados;
        } finally {
            em.close();
        }
    }

    @Override
    public DTPaqueteActividadTuristica obtenerPaqueteCosto(String nombre) {
        EntityManager em = emf.createEntityManager();
        float costo = 0;
        List<EActividadTuristica> resultados_consulta = new LinkedList<>();
        List<DTActividadTuristica> resultados = new LinkedList<>();
        try {

            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", nombre)
                    .getSingleResult();
            EPaqueteActividadTuristica paquete = em.find(EPaqueteActividadTuristica.class, ePaquete.getId());
            resultados_consulta = paquete.getActividades();

            for (EActividadTuristica p : resultados_consulta) {
                if (p.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    costo = costo + p.getCosto();
                }
            }

            return new DTPaqueteActividadTuristica(
                    ePaquete.getNombre(),
                    ePaquete.getDescripcion(),
                    ePaquete.getValidez(),
                    ePaquete.getDescuento(),
                    ePaquete.getFechaAlta(),
                    costo * ((100 - ePaquete.getDescuento()) / 100)
            );
        } catch (Exception e) {
            return new DTPaqueteActividadTuristica();
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesRelacionadas(String nomPaquete) {
        EntityManager em = emf.createEntityManager();
        List<EActividadTuristica> resultados_consulta = new LinkedList<>();
        List<DTActividadTuristica> resultados = new LinkedList<>();

        try {
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", nomPaquete)
                    .getSingleResult();

            EPaqueteActividadTuristica paquete = em.find(EPaqueteActividadTuristica.class, ePaquete.getId());
            resultados_consulta = paquete.getActividades();

            for (EActividadTuristica p : resultados_consulta) {
                if (p.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(p.getId(), p.getNombre(), p.getDescripcion(), p.getDuracion(), p.getCosto(), p.getCiudad(), p.getFechaAlta());
                    resultados.add(dtActividadTuristica);
                }
            }
            return resultados;
        } catch (Exception e) {
            return resultados;
        } finally {
            em.close();
        }
    }

    @Override
    public void altaPaqueteActividadTuristica(DTPaqueteActividadTuristica dtPaquete, byte[] foto) {
        EntityManager em = emf.createEntityManager();

        EPaqueteActividadTuristica nuevoPaquete = new EPaqueteActividadTuristica(dtPaquete.getNombre(), dtPaquete.getDescripcion(), dtPaquete.getValidez(), dtPaquete.getDescuento(), dtPaquete.getFechaAlta(), foto);
        try {
            em.getTransaction().begin();
            em.persist(nuevoPaquete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void existeDepartamento(String nombreDepartamento) throws MyException {
        EntityManager em = emf.createEntityManager();
        String consulta = "select d from EDepartamento d where d.nombre = :nombreDepartamento";
        List<EDepartamento> resultado = new LinkedList<>();

        try {
            resultado = em.createQuery(consulta, EDepartamento.class)
                    .setParameter("nombreDepartamento", nombreDepartamento).getResultList();
        } catch (Exception e) {
            throw new MyException("Algo salio mal consultando la base de datos. ");
        } finally {
            em.close();
        }

        if (!resultado.isEmpty()) {
            throw new MyException("Ya existe un departamento con ese nombre en el sistema.");
        }
    }

    public void altaDepartamento(DTDepartamento dtDepto) {
        EntityManager em = emf.createEntityManager();
        try {
            EDepartamento nuevoDepartamento = new EDepartamento(dtDepto.getNombre(),
                    dtDepto.getDescripcion(), dtDepto.getURL());

            em.getTransaction().begin();
            em.persist(nuevoDepartamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTDepartamento> obtenerDepartamentos() {
        EntityManager em = emf.createEntityManager();
        String consulta = "select d from EDepartamento d";
        List<DTDepartamento> dtDepartamentos = new LinkedList<>();

        try {
            List<EDepartamento> resultado = em.createQuery(consulta, EDepartamento.class).getResultList();
            /**
             * una vez obtenida la lista de EDepartamentos los parseo a
             * DTDepartamentos para respetar la arquitectura de capas y no pasar
             * objetos
             */
            for (EDepartamento ed : resultado) {
                DTDepartamento dtDepartamento = new DTDepartamento(ed.getId(), ed.getNombre(),
                        ed.getDescripcion(), ed.getUrl());

                dtDepartamentos.add(dtDepartamento);
            }

            return dtDepartamentos;
        } catch (Exception e) {
            return dtDepartamentos;
        } finally {
            em.close();
        }
    }

    @Override
    public void existeSalidaTuristica(String nombre) throws MyException {
        EntityManager em = emf.createEntityManager();
        String consulta = "SELECT s from ESalidaTuristica s where s.nombre = :nombreSalida";
        List<ESalidaTuristica> resultado = new LinkedList<>();

        try {
            resultado = em.createQuery(consulta, ESalidaTuristica.class)
                    .setParameter("nombreSalida", nombre)
                    .getResultList();
        } catch (Exception e) {
            throw new MyException("Algo salio mal consultando la base de datos. ");
        } finally {
            em.close();
        }

        if (!resultado.isEmpty()) {
            throw new MyException("Ya existe una salida turistica con el nombre ingresado. ");
        }
    }

    @Override
    public void altaSalidaTuristica(DTSalidaTuristica dtSalidaTuristica, String nombreActividad) throws MyException {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String query = "SELECT a FROM EActividadTuristica a WHERE a.nombre = :nombreActividad";
            EActividadTuristica eActividadTuristica = em.createQuery(query, EActividadTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getSingleResult();
            if (eActividadTuristica.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                ESalidaTuristica nuevaSalida = new ESalidaTuristica(
                        dtSalidaTuristica.getNombre(),
                        dtSalidaTuristica.getCantidadMaxTuristas(),
                        dtSalidaTuristica.getFechaSalida(),
                        dtSalidaTuristica.getLugar(),
                        dtSalidaTuristica.getFechaAlta(),
                        dtSalidaTuristica.getImagen(),
                        eActividadTuristica,
                        0
                );

                em.persist(nuevaSalida);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            throw new MyException("ERROR! Algo salio durante el alta de la salida turistica. ");
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTSalidaTuristica> obtenerSalidasTuristicas(String nombreActividad) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> resultados;
        ArrayList<DTSalidaTuristica> dtActividadesTuristicas = new ArrayList<>();

        try {
            String query = "select s from ESalidaTuristica s where s.eActividadTuristica.nombre = :nombreActividad";
            resultados = em.createQuery(query, ESalidaTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getResultList();

            for (ESalidaTuristica s : resultados) {
                dtActividadesTuristicas.add(
                        new DTSalidaTuristica(
                                s.getNombre(),
                                s.getCantidadMaxTuristas(),
                                s.getFechaSalida(),
                                s.getLugar(),
                                s.getFechaAlta()
                        )
                );
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    public ArrayList<DTSalidaTuristica> obtenerSalidasTuristicasVigentes(String nombreActividad) {
        EntityManager em = emf.createEntityManager();
        List<ESalidaTuristica> resultados;
        ArrayList<DTSalidaTuristica> dtActividadesTuristicas = new ArrayList<>();

        try {
            String query = "select s from ESalidaTuristica s where s.eActividadTuristica.nombre = :nombreActividad";
            resultados = em.createQuery(query, ESalidaTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getResultList();

            LocalDate currentDate = LocalDate.now();

            for (ESalidaTuristica s : resultados) {
                LocalDate salidaDate = s.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (!currentDate.isAfter(salidaDate)) {
                    dtActividadesTuristicas.add(
                            new DTSalidaTuristica(
                                    s.getNombre(),
                                    s.getCantidadMaxTuristas(),
                                    s.getFechaSalida(),
                                    s.getLugar(),
                                    s.getFechaAlta()
                            )
                    );
                }
            }
            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public float obtenerCostoActividad(String nombreActividad) {
        EntityManager em = emf.createEntityManager();

        try {
            String query = "select a from EActividadTuristica a where a.nombre = :nombreActividad";
            EActividadTuristica eActividadTuristica = em.createQuery(query, EActividadTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getSingleResult();

            return eActividadTuristica.getCosto();
        } catch (Exception e) {
            return 0.0f;
        } finally {
            em.close();
        }
    }

    @Override
    public void altaInscripcion(DTInscripcion dtInscripcion, String nombreSalida, String nickname) throws MyException {
        EntityManager em = emf.createEntityManager();

        ESalidaTuristica eSalidaTuristica = obtenerSalidaPorNombre(nombreSalida);

        // Validamos que hayan cupos para la cantidad de turistas ingresados
        int cuposDisponibles = eSalidaTuristica.getCantidadMaxTuristas() - obtenerTotalTuristasInscriptos(nombreSalida);
        if (dtInscripcion.getCantidadTuristas() > cuposDisponibles) {
            throw new MyException("La cantidad de turistas ingresada excede los cupos disponibles para la salida!");
        }

        String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
        ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                .setParameter("nickname", nickname)
                .getSingleResult();

        // Validamos que el turista no este inscripto en la salida
        String auxQuery = "SELECT i FROM EInscripcion i WHERE i.eTurista = :turista AND i.eSalidaTuristica = :salida";
        List<EInscripcion> eInscripcionList = em.createQuery(auxQuery, EInscripcion.class)
                .setParameter("turista", eTurista)
                .setParameter("salida", eSalidaTuristica)
                .getResultList();

        if (!eInscripcionList.isEmpty()) {
            throw new MyException("El turista ya se encuentra inscripto en esta salida!");
        }

        try {
            em.getTransaction().begin();

            EInscripcion nuevaInscripcion = new EInscripcion(
                    eTurista,
                    eSalidaTuristica,
                    dtInscripcion.getFecha(),
                    dtInscripcion.getCantidadTuristas(),
                    dtInscripcion.getCostoTotal()
            );

            em.persist(nuevaInscripcion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new MyException("ERROR! Algo salio durante el alta de la inscripcion. ");
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerPaqueteNombre() {
        EntityManager em = emf.createEntityManager();

        try {

            Query query = em.createQuery("select p.nombre from EPaqueteActividadTuristica p where p.ESTADOPAQUETE = 0");
            List<String> resultado = query.getResultList();

            return resultado;

        } catch (Exception e) {
            List<String> resultado = new LinkedList<>();
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTPaqueteActividadTuristica> obtenerPaquetes() {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTPaqueteActividadTuristica> dtresultados = new ArrayList<>();
        try {
            Query query = em.createNativeQuery("select distinct nombre from paquetes inner join PAQUETE_ACTIVIDAD ON paquetes.ID = PAQUETE_ACTIVIDAD.PAQUETE_ID");
            List<String> resultados = query.getResultList();

            for (String epaquete : resultados) {
                dtresultados.add(obtenerPaqueteCosto(epaquete));
            }
            return dtresultados;

        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public DTSalidaTuristica obtenerSalidaTuristica(String nombreSalida) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            ESalidaTuristica eSalidaTuristica = obtenerSalidaPorNombre(nombreSalida);

            int vistasActuales = eSalidaTuristica.getCantidadVistas();

            Logger.getLogger("Log BD").log(Level.SEVERE, "esalida.getcantvistas: "
                    + eSalidaTuristica.getCantidadVistas() + "vistas actuales: " + vistasActuales);

            vistasActuales += 1;
            eSalidaTuristica.setCantidadVistas(vistasActuales);

            Logger.getLogger("Log BD").log(Level.SEVERE, "esalida.getcantvistas: "
                    + eSalidaTuristica.getCantidadVistas() + "vistas actuales: " + vistasActuales);
            em.merge(eSalidaTuristica);
            //em.persist(eSalidaTuristica);
            //em.refresh(eSalidaTuristica);
            em.getTransaction().commit();

            return new DTSalidaTuristica(
                    eSalidaTuristica.getNombre(),
                    eSalidaTuristica.getCantidadMaxTuristas(),
                    eSalidaTuristica.getFechaSalida(),
                    eSalidaTuristica.getLugar(),
                    eSalidaTuristica.getFechaAlta(),
                    eSalidaTuristica.getImagen()
            );
        } catch (Exception e) {
            return new DTSalidaTuristica();
        } finally {
            em.close();
        }
    }

    @Override
    public DTPaqueteActividadTuristica obtenerPaquete(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {

            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", nombre)
                    .getSingleResult();
            return new DTPaqueteActividadTuristica(
                    ePaquete.getNombre(),
                    ePaquete.getDescripcion(),
                    ePaquete.getValidez(),
                    ePaquete.getDescuento(),
                    ePaquete.getFechaAlta()
            );
        } catch (Exception e) {
            return new DTPaqueteActividadTuristica();
        } finally {
            em.close();
        }
    }

    @Override
    public DTDepartamento CU11obtenerDepartamentoActividad(String nombreActividad) {
        EntityManager em = emf.createEntityManager();
        try {

            EActividadTuristica eActividad = em.createQuery("select p from EActividadTuristica p where p.nombre = :nombreActividad",
                    EActividadTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getSingleResult();
            EActividadTuristica actividad = em.find(EActividadTuristica.class, eActividad.getId());
            EDepartamento eDepartamento = actividad.getEDepartamento();
            DTDepartamento dtDepartamento = new DTDepartamento(eDepartamento.getNombre(), eDepartamento.getDescripcion(), eDepartamento.getUrl());
            return dtDepartamento;

        } catch (Exception e) {
            return new DTDepartamento();
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerActividadesTuristicasCU10(String departamento, String paquete) {
        EntityManager em = emf.createEntityManager();

        try {

            EDepartamento eDepartamento = em.createQuery("select d from EDepartamento d where d.nombre = :nombre",
                    EDepartamento.class)
                    .setParameter("nombre", departamento)
                    .getSingleResult();
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", paquete)
                    .getSingleResult();

            Query query = em.createNativeQuery("select nombre from actividadTuristica where EDEPARTAMENTO_ID = ?1 AND ESTADOACTIVIDAD = 'CONFIRMADA'AND actividadTuristica.id not in (select ACTIVIDAD_ID from paquetes join PAQUETE_ACTIVIDAD ON PAQUETE_ID = paquetes.id where paquetes.id = ?3)")
                    .setParameter(1, eDepartamento.getId())
                    .setParameter(3, ePaquete.getId());
            List<String> resultado = query.getResultList();
            return resultado;

        } catch (Exception e) {
            List<String> resultado = new LinkedList<>();
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public void agregarActividadPaquete(String paquete, String actividad) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", paquete)
                    .getSingleResult();

            EActividadTuristica eActividad = em.createQuery("SELECT c FROM EActividadTuristica c WHERE c.nombre = :nombreActividad",
                    EActividadTuristica.class)
                    .setParameter("nombreActividad", actividad)
                    .getSingleResult();
            ePaquete.getActividades().add(eActividad);

            em.persist(ePaquete);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
    }

    private ESalidaTuristica obtenerSalidaPorNombre(String nombreSalida) {
        EntityManager em = emf.createEntityManager();
        String query = "select s from ESalidaTuristica s where s.nombre = :nombreSalida";

        return em.createQuery(query, ESalidaTuristica.class)
                .setParameter("nombreSalida", nombreSalida)
                .getSingleResult();
    }

    private int obtenerTotalTuristasInscriptos(String nombreSalida) {
        EntityManager em = emf.createEntityManager();

        String querySalida = "SELECT s FROM ESalidaTuristica s WHERE s.nombre = :nombreSalida";
        ESalidaTuristica eSalidaTuristica = em.createQuery(querySalida, ESalidaTuristica.class)
                .setParameter("nombreSalida", nombreSalida)
                .getSingleResult();

        String queryTotal = "SELECT SUM(i.cantidadTuristas) FROM EInscripcion i WHERE i.eSalidaTuristica = :salida";

        long total;
        try {
            total = em.createQuery(queryTotal, Long.class)
                    .setParameter("salida", eSalidaTuristica)
                    .getSingleResult();
        } catch (NullPointerException e) {
            total = 0;
        }

        return (int) total;
    }

    @Override
    public void altaCategoria(String nombre) throws MyException {
        EntityManager em = emf.createEntityManager();

        String query = "SELECT c FROM ECategoria c WHERE c.nombre = :nombre";
        List<ECategoria> eCategorias = em.createQuery(query, ECategoria.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (!eCategorias.isEmpty()) {
            throw new MyException("Ya existe una categoria con el nombre ingresado");
        }

        try {
            em.getTransaction().begin();

            ECategoria nuevaCategoria = new ECategoria(
                    nombre
            );

            em.persist(nuevaCategoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new MyException("ERROR! Algo salio durante el alta de la categoria. ");
        } finally {
            em.close();
        }
    }

    @Override
    public boolean compraExiste(DTCompraPaquete compra) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryPaquete = "SELECT p FROM EPaqueteActividadTuristica p WHERE p.nombre = :nombre";
            EPaqueteActividadTuristica ePaquete = em.createQuery(queryPaquete, EPaqueteActividadTuristica.class)
                    .setParameter("nombre", compra.getPAQUETE().getNombre())
                    .getSingleResult();

            Query query = em.createNativeQuery(" select * from COMPRAS_PAQUETE  where COMPRADOR_ID = ?1 AND PAQUETE_ID = ?3")
                    .setParameter(1, compra.getCOMPRADOR().getId())
                    .setParameter(3, ePaquete.getId());
            List<String> resultados = query.getResultList();

            if (resultados.isEmpty()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTCategoria> obtenerCategorias() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT c FROM ECategoria c";
        List<DTCategoria> dtCategorias = new LinkedList<>();

        try {
            List<ECategoria> resultado = em.createQuery(query, ECategoria.class).getResultList();

            for (ECategoria e : resultado) {
                dtCategorias.add(new DTCategoria(e.getId(), e.getNombre()));
            }

            return dtCategorias;
        } catch (Exception e) {
            return dtCategorias;
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTActividadTuristica> obtenerActividadesSinConfirmar() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT a FROM EActividadTuristica a";
        List<DTActividadTuristica> dtActividadesTuristicas = new LinkedList<>();
        List<EActividadTuristica> resultado = new LinkedList<>();
        try {
            resultado = em.createQuery(query, EActividadTuristica.class).getResultList();

            for (EActividadTuristica r : resultado) {
                if (r.getEstadoActividad() == EstadoActividad.AGREGADA) {
                    DTActividadTuristica dtActividadTuristica = new DTActividadTuristica(r.getId(),
                            r.getNombre(), r.getDescripcion());
                    dtActividadesTuristicas.add(dtActividadTuristica);
                }
            }

            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public void validarActividad(Long id, EstadoActividad estado) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            EActividadTuristica eActividadTuristica = em.find(EActividadTuristica.class, id);
            eActividadTuristica.setEstadoActividad(estado);
            em.persist(eActividadTuristica);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTActividadTuristica> obtenerActividadesTuristicas(String nombreDepartamento, Long idProveedor) {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTActividadTuristica> dtActividadesTuristicas = new ArrayList<>();

        try {
            EProveedor eProveedor = em.find(EProveedor.class, idProveedor);

            for (EActividadTuristica actividad : eProveedor.getActividades()) {

                if (actividad.getEDepartamento().getNombre().equals(nombreDepartamento)) {

                    if (actividad.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                        dtActividadesTuristicas.add(
                                new DTActividadTuristica(
                                        actividad.getNombre(),
                                        actividad.getDescripcion(),
                                        actividad.getDuracion(),
                                        actividad.getCosto(),
                                        actividad.getCiudad(),
                                        actividad.getFechaAlta()
                                ));
                    }

                }

            }

            return dtActividadesTuristicas;
        } catch (Exception e) {
            return dtActividadesTuristicas;
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerPaqueteNombresActividades() {
        EntityManager em = emf.createEntityManager();

        try {

            Query query = em.createNativeQuery("select distinct nombre from paquetes inner join PAQUETE_ACTIVIDAD ON paquetes.ID = PAQUETE_ACTIVIDAD.PAQUETE_ID");

            List<String> resultado = query.getResultList();
            return resultado;

        } catch (Exception e) {
            List<String> resultado = new LinkedList<>();
            return resultado;
        } finally {
            em.close();
        }
    }

    @Override
    public DTActividadTuristica obtenerFotoActividadTuristicaID(String actividad) {
        EntityManager em = emf.createEntityManager();
        try {

            EActividadTuristica Eactividad = em.createQuery("select a from EActividadTuristica a where a.nombre = :nombreActividad",
                    EActividadTuristica.class)
                    .setParameter("nombreActividad", actividad)
                    .getSingleResult();
            return new DTActividadTuristica(Eactividad.getId(),
                    Eactividad.getNombre(),
                    Eactividad.getDescripcion(),
                    Eactividad.getDuracion(),
                    Eactividad.getCosto(),
                    Eactividad.getCiudad(),
                    Eactividad.getFechaAlta()
            );

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public byte[] obtenerFotoPaqueteActividadTuristica(String selectedPaquete) {
        EntityManager em = emf.createEntityManager();
        try {

            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", selectedPaquete)
                    .getSingleResult();
            return ePaquete.getFoto();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public void agregarCompraPaquete(DTCompraPaquete compra) {
        EntityManager em = emf.createEntityManager();
        System.out.println(compra.getPAQUETE().getNombre());
        try {
            EPaqueteActividadTuristica ePaquete = em.createQuery("select p from EPaqueteActividadTuristica p where p.nombre = :nombrePaquete",
                    EPaqueteActividadTuristica.class)
                    .setParameter("nombrePaquete", compra.getPAQUETE().getNombre())
                    .getSingleResult();
            ETurista eTurista = em.createQuery("select t from ETurista t where t.id = :turistaId",
                    ETurista.class)
                    .setParameter("turistaId", compra.getCOMPRADOR().getId())
                    .getSingleResult();
            ECompraPaquete nuevaCompra;
            nuevaCompra = new ECompraPaquete(
                    eTurista,
                    ePaquete,
                    compra.getCANTTURISTAS(),
                    compra.getVENCIMIENTO(),
                    compra.getFECHACOMPRA(),
                    compra.getCOSTO());

            em.getTransaction().begin();
            em.persist(nuevaCompra);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<DTPaqueteActividadTuristica> obtenerPaquetesCompradosDisponibles(Long idTurista, String nombreSalida, int cantTuristas) {
        EntityManager em = emf.createEntityManager();

        List<DTPaqueteActividadTuristica> dtresultados = new LinkedList<>();
        try {
            ETurista eTurista = em.find(ETurista.class, idTurista);

            // Obtenemos los paquetes comprados por el turista
            String queryPaquetes = "SELECT c.PAQUETE FROM ECompraPaquete c "
                    + "WHERE c.COMPRADOR = :turista AND c.VENCIMIENTO > CURRENT_DATE AND c.CANTTURISTAS >= :cantTuristas";
            List<EPaqueteActividadTuristica> ePaquetesTurista = em.createQuery(queryPaquetes, EPaqueteActividadTuristica.class)
                    .setParameter("turista", eTurista)
                    .setParameter("cantTuristas", cantTuristas)
                    .getResultList();

            if (ePaquetesTurista.isEmpty()) {
                return dtresultados; // Si no compro paquetes aún return vacío
            }
            // Obtenemos la actividad correspondiente a la salida ingresada
            String queryActividad = "SELECT a.nombre FROM EActividadTuristica a JOIN ESalidaTuristica s "
                    + "ON s.eActividadTuristica = a WHERE s.nombre = :nombreSalida";
            String nombreActividad = em.createQuery(queryActividad, String.class)
                    .setParameter("nombreSalida", nombreSalida)
                    .getSingleResult();

            // Obtenemos los paquete disponibles para la nombreActividad
            String queryPaquetesActividad = "SELECT a.paquetes FROM EActividadTuristica a WHERE a.nombre = :actividad";
            List<EPaqueteActividadTuristica> ePaquetesActividad = em.createQuery(queryPaquetesActividad, EPaqueteActividadTuristica.class)
                    .setParameter("actividad", nombreActividad)
                    .getResultList();

            for (EPaqueteActividadTuristica ePaquete : ePaquetesActividad) {
                if (ePaquetesTurista.stream()
                        .anyMatch(e -> (e.getId().compareTo(ePaquete.getId())) == 0)) {

                    dtresultados.add(new DTPaqueteActividadTuristica(
                            ePaquete.getNombre(),
                            ePaquete.getDescripcion(),
                            ePaquete.getValidez(),
                            ePaquete.getDescuento(),
                            ePaquete.getFechaAlta()
                    )
                    );
                }

            }
            return dtresultados;

        } catch (Exception e) {
            return dtresultados;
        } finally {
            em.close();
        }

    }

    @Override
    public List<DTTop> obtenerTop() {
        EntityManager em = emf.createEntityManager();
        List<DTTop> resultadoTopSort = new LinkedList<>();

        try {
            /*new version*/
            String consultaActividadSort = "SELECT id,nombre,cantidadvistas FROM turismouy.actividadTuristica order by cantidadvistas desc;";
            String consultaSalidaSort = "SELECT id,nombre,cantidadvistas FROM turismouy.salidaTuristica order by cantidadvistas desc;";

            //List<> resultadoSort = new LinkedList<>();
            List<EActividadTuristica> actividadTuristicaSort = new LinkedList<>();
            List<ESalidaTuristica> salidaTuristicaSort = new LinkedList<>();

            int index = 1;

            //guardo las actividades como dttop                
            actividadTuristicaSort = em.createNativeQuery(consultaActividadSort, EActividadTuristica.class).getResultList();
            for (EActividadTuristica a : actividadTuristicaSort) {
                DTTop dtop = new DTTop(0, a.getId(), a.getNombre(), "ACTIVIDAD", a.getCantidadVistas());
                resultadoTopSort.add(dtop);
            }

            //guardo las salidas como dttop
            salidaTuristicaSort = em.createNativeQuery(consultaSalidaSort, ESalidaTuristica.class).getResultList();
            for (ESalidaTuristica s : salidaTuristicaSort) {
                DTTop dtop = new DTTop(0, s.getId(), s.getNombre(), "SALIDA", s.getCantidadVistas());
                resultadoTopSort.add(dtop);
            }

            //ordeno la lista
            Collections.sort(resultadoTopSort, Comparator.comparingInt(DTTop::getCantidadVistas).reversed());
            //actualizo el indice
            for (DTTop d : resultadoTopSort) {
                d.setPos(index);
                index += 1;
            }
            //devuelvo las 10 mejores
            while (index > 10) {
                resultadoTopSort.remove(index);
                index -= 1;
            }

            return resultadoTopSort;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return resultadoTopSort;
        } finally {
            em.close();
        }
    }

    @Override
    public DTInscripcion obtenerInscripcion(String nickname, String nombreSalida) {
        EntityManager em = emf.createEntityManager();

        try {
            ESalidaTuristica eSalidaTuristica = obtenerSalidaPorNombre(nombreSalida);

            String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
            ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();

            String auxQuery = "SELECT i FROM EInscripcion i WHERE i.eTurista = :turista AND i.eSalidaTuristica = :salida";
            EInscripcion eInscripcion = em.createQuery(auxQuery, EInscripcion.class)
                    .setParameter("turista", eTurista)
                    .setParameter("salida", eSalidaTuristica)
                    .getSingleResult();

            DTInscripcion dtInscripcion = new DTInscripcion();
            DTSalidaTuristica dtSalida = new DTSalidaTuristica();
            DTActividadTuristica dtActividad = new DTActividadTuristica();
            DTTurista dtTurista = new DTTurista();

            dtActividad.setNombre(eInscripcion.getESalidaTuristica().getEActividadTuristica().getNombre());

            dtSalida.setNombre(eInscripcion.getESalidaTuristica().getNombre());
            dtSalida.setDtActividadTuristica(dtActividad);

            dtTurista.setNickname(eInscripcion.getETurista().getNickname());
            dtTurista.setName(eInscripcion.getETurista().getName());

            dtInscripcion.setSalidaTuristica(dtSalida);
            dtInscripcion.setTurista(dtTurista);
            dtInscripcion.setCantidadTuristas(eInscripcion.getCantidadTuristas());
            dtInscripcion.setCostoTotal(eInscripcion.getCostoTotal());
            dtInscripcion.setFecha(eInscripcion.getFecha());

            return dtInscripcion;

        } catch (Exception e) {
            return new DTInscripcion();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTBusqueda> obtenerBusqueda(String peticionBusqueda) {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTBusqueda> resultadosBusqueda = new ArrayList<>();

        try {

            /*caso filtrado por nombre y ordenado alfabeticamente*/
            String actividadQuery = "select a from EActividadTuristica a where a.nombre like :nombre or a.descripcion like :nombre";
            String paqueteQuery = "select p from EPaqueteActividadTuristica p where p.nombre like :nombre or p.descripcion like :nombre";

            List<EActividadTuristica> resultadoActividad = new ArrayList<>();
            List<EPaqueteActividadTuristica> resultadoPaquete = new ArrayList<>();

            resultadoActividad = em.createQuery(actividadQuery, EActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            resultadoPaquete = em.createQuery(paqueteQuery, EPaqueteActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            for (EActividadTuristica a : resultadoActividad) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTBusqueda dtb = new DTBusqueda(a.getId(),
                            a.getNombre(), a.getFechaAlta(), a.getFechaAlta().toString(),
                            "",
                            "Actividad", a.getDescripcion(),
                            a.getEDepartamento().getNombre());

                    String categorias = "";

                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }

                    dtb.setCategorias(categorias);

                    resultadosBusqueda.add(dtb);
                }
            }

            for (EPaqueteActividadTuristica p : resultadoPaquete) {
                DTBusqueda dtb = new DTBusqueda(p.getId(),
                        p.getNombre(), p.getFechaAlta(), p.getFechaAlta().toString(),
                        "",
                        "Paquete", p.getDescripcion(), "");

                String departamentos = "";

                for (EActividadTuristica a : p.getActividades()) {
                    if (!departamentos.contains(a.getEDepartamento().getNombre())) {
                        departamentos += a.getEDepartamento().getNombre() + ",";
                    }
                }

                dtb.setDepartamento(departamentos);

                String categorias = "";

                for (EActividadTuristica a : p.getActividades()) {
                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }
                }

                dtb.setCategorias(categorias);

                resultadosBusqueda.add(dtb);
            }

            return resultadosBusqueda;
        } catch (Exception e) {
            return resultadosBusqueda;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaFecha(String peticionBusqueda) {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTBusqueda> resultadosBusqueda = new ArrayList<>();

        try {
            String actividadQuery = "select a from EActividadTuristica a where a.nombre like :nombre or a.descripcion like :nombre";
            String paqueteQuery = "select p from EPaqueteActividadTuristica p where p.nombre like :nombre or p.descripcion like :nombre";

            List<EActividadTuristica> resultadoActividad = new ArrayList<>();
            List<EPaqueteActividadTuristica> resultadoPaquete = new ArrayList<>();

            resultadoActividad = em.createQuery(actividadQuery, EActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            resultadoPaquete = em.createQuery(paqueteQuery, EPaqueteActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            for (EActividadTuristica a : resultadoActividad) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTBusqueda dtb = new DTBusqueda(a.getId(),
                            a.getNombre(), a.getFechaAlta(), a.getFechaAlta().toString(),
                            "",
                            "Actividad", a.getDescripcion(),
                            a.getEDepartamento().getNombre());

                    String categorias = "";

                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }

                    dtb.setCategorias(categorias);

                    resultadosBusqueda.add(dtb);
                }
            }

            /*para los paquetes que pueden contener varios departamentos
            recorro los departamentos de cada actividad que contenga
            un paquete y lo agrego a la lista si es nuevo*/
            for (EPaqueteActividadTuristica p : resultadoPaquete) {
                DTBusqueda dtb = new DTBusqueda(p.getId(),
                        p.getNombre(), p.getFechaAlta(), p.getFechaAlta().toString(),
                        "",
                        "Paquete", p.getDescripcion(), "");

                String departamentos = "";

                for (EActividadTuristica a : p.getActividades()) {
                    if (!departamentos.contains(a.getEDepartamento().getNombre())) {
                        departamentos += a.getEDepartamento().getNombre() + ",";
                    }
                }

                dtb.setDepartamento(departamentos);

                String categorias = "";

                for (EActividadTuristica a : p.getActividades()) {
                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }
                }

                dtb.setCategorias(categorias);

                resultadosBusqueda.add(dtb);
            }
            Collections.sort(resultadosBusqueda, Comparator.comparing(DTBusqueda::getFechaAlta).reversed());
            return resultadosBusqueda;
        } catch (Exception e) {
            return resultadosBusqueda;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaDepartamento(String peticionBusqueda, String nombreDepartamento) {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTBusqueda> resultadosBusqueda = new ArrayList<>();

        try {
            /*caso filtrado por nombre y ordenado alfabeticamente*/
            String actividadQuery = "select a from EActividadTuristica a where a.nombre like :nombre or a.descripcion like :nombre";
            String paqueteQuery = "select p from EPaqueteActividadTuristica p where p.nombre like :nombre or p.descripcion like :nombre";

            List<EActividadTuristica> resultadoActividad = new ArrayList<>();
            List<EPaqueteActividadTuristica> resultadoPaquete = new ArrayList<>();

            resultadoActividad = em.createQuery(actividadQuery, EActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            resultadoPaquete = em.createQuery(paqueteQuery, EPaqueteActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            for (EActividadTuristica a : resultadoActividad) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTBusqueda dtb = new DTBusqueda(a.getId(),
                            a.getNombre(), a.getFechaAlta(), a.getFechaAlta().toString(),
                            "",
                            "Actividad", a.getDescripcion(),
                            a.getEDepartamento().getNombre());

                    String categorias = "";

                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }

                    dtb.setCategorias(categorias);

                    if (dtb.getDepartamento().equals(nombreDepartamento)) {
                        resultadosBusqueda.add(dtb);
                    }
                }
            }

            for (EPaqueteActividadTuristica p : resultadoPaquete) {
                DTBusqueda dtb = new DTBusqueda(p.getId(),
                        p.getNombre(), p.getFechaAlta(), p.getFechaAlta().toString(),
                        "",
                        "Paquete", p.getDescripcion(), "");

                String departamentos = "";

                for (EActividadTuristica a : p.getActividades()) {
                    if (!departamentos.contains(a.getEDepartamento().getNombre())) {
                        departamentos += a.getEDepartamento().getNombre() + ",";
                    }
                }

                dtb.setDepartamento(departamentos);

                String categorias = "";

                for (EActividadTuristica a : p.getActividades()) {
                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }
                }

                dtb.setCategorias(categorias);

                if (dtb.getDepartamento().contains(nombreDepartamento)) {
                    resultadosBusqueda.add(dtb);
                }
            }
            return resultadosBusqueda;
        } catch (Exception e) {
            return resultadosBusqueda;
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<DTBusqueda> ordenarBusquedaCategoria(String peticionBusqueda, String categoria) {
        EntityManager em = emf.createEntityManager();
        ArrayList<DTBusqueda> resultadosBusqueda = new ArrayList<>();
        try {
            /*caso filtrado por nombre y ordenado alfabeticamente*/
            String actividadQuery = "select a from EActividadTuristica a where a.nombre like :nombre or a.descripcion like :nombre";
            String paqueteQuery = "select p from EPaqueteActividadTuristica p where p.nombre like :nombre or p.descripcion like :nombre";

            List<EActividadTuristica> resultadoActividad = new ArrayList<>();
            List<EPaqueteActividadTuristica> resultadoPaquete = new ArrayList<>();

            resultadoActividad = em.createQuery(actividadQuery, EActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            resultadoPaquete = em.createQuery(paqueteQuery, EPaqueteActividadTuristica.class).
                    setParameter("nombre", "%" + peticionBusqueda + "%").getResultList();

            for (EActividadTuristica a : resultadoActividad) {
                if (a.getEstadoActividad() == EstadoActividad.CONFIRMADA) {
                    DTBusqueda dtb = new DTBusqueda(a.getId(),
                            a.getNombre(), a.getFechaAlta(), a.getFechaAlta().toString(),
                            "",
                            "Actividad", a.getDescripcion(),
                            a.getEDepartamento().getNombre());

                    String categorias = "";

                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }

                    dtb.setCategorias(categorias);

                    if (dtb.getCategorias().contains(categoria)) {
                        resultadosBusqueda.add(dtb);
                    }
                }
            }

            for (EPaqueteActividadTuristica p : resultadoPaquete) {
                DTBusqueda dtb = new DTBusqueda(p.getId(),
                        p.getNombre(), p.getFechaAlta(), p.getFechaAlta().toString(),
                        "",
                        "Paquete", p.getDescripcion(), "");

                String departamentos = "";

                for (EActividadTuristica a : p.getActividades()) {
                    departamentos += a.getEDepartamento().getNombre() + ",";
                }

                dtb.setDepartamento(departamentos);

                String categorias = "";

                /**
                 * @a es cada una de las actividades turisticas que contiene el
                 * paquete voy recorriendo estas actividades y agrego el nombre
                 * de la categoria solo si es una categoria nueva por ultimo
                 * agrego este dtb a la lista solo si contiene la categoria que
                 * me interesa
                 */
                for (EActividadTuristica a : p.getActividades()) {
                    for (ECategoria c : a.getCategorias()) {
                        if (!categorias.contains(c.getNombre())) {
                            categorias += c.getNombre() + ",";
                        }
                    }
                }

                dtb.setCategorias(categorias);

                if (dtb.getCategorias().contains(categoria)) {
                    resultadosBusqueda.add(dtb);
                }
            }

            return resultadosBusqueda;
        } catch (Exception e) {
            return resultadosBusqueda;
        } finally {
            em.close();
        }
    }

    @Override
    public int obtenerCantidadFavoritos(String nombreActividad) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryActividad = "SELECT a FROM EActividadTuristica a WHERE a.nombre = :nombreActividad";
            EActividadTuristica eActividad = em.createQuery(queryActividad, EActividadTuristica.class)
                    .setParameter("nombreActividad", nombreActividad)
                    .getSingleResult();

            Query queryCount = em.createNativeQuery(
                    "SELECT COUNT(*) FROM turista_actividadTuristica WHERE eActividadesFavoritas_ID = ?actividadId");
            queryCount.setParameter("actividadId", eActividad.getId());

            return ((Number) queryCount.getSingleResult()).intValue();
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }

    @Override
    public List<String> obtenerActividadesFavoritas(String nickname) {
        EntityManager em = emf.createEntityManager();
        List<String> actividadesFavoritas = new ArrayList<>();

        try {
            String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
            ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();

            for (EActividadTuristica eActividad : eTurista.getEActividadesFavoritas()) {
                actividadesFavoritas.add(eActividad.getNombre());
            }

            return actividadesFavoritas;

        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void agregarAFavoritos(Long idActividad, String nickname) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
            ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();

            List<String> favoritas = obtenerActividadesFavoritas(nickname);
            EActividadTuristica eActividad = em.find(EActividadTuristica.class, idActividad);

            if (!favoritas.contains(eActividad.getNombre())) {
                eTurista.getEActividadesFavoritas().add(eActividad);

                em.getTransaction().begin();
                em.merge(eTurista);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarDeFavoritos(Long idActividad, String nickname) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryTurista = "SELECT t FROM ETurista t WHERE t.nickname = :nickname";
            ETurista eTurista = em.createQuery(queryTurista, ETurista.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();

            List<String> favoritas = obtenerActividadesFavoritas(nickname);
            EActividadTuristica eActividad = em.find(EActividadTuristica.class, idActividad);

            if (favoritas.contains(eActividad.getNombre())) {
                eTurista.getEActividadesFavoritas().remove(eActividad);

                em.getTransaction().begin();
                em.merge(eTurista);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
