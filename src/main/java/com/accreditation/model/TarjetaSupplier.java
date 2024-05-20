package com.accreditation.model;
// <editor-fold defaultstate="collapsed" desc="imports">

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
/**
* Java
*/
import java.time.LocalDateTime;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
/**
* Jmoordb
*/
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypePK;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;
/**
* MongoDB
*/
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import com.accreditation.model.Tarjeta;
import com.accreditation.model.*;


// </editor-fold>
@RequestScoped
public class TarjetaSupplier  implements Serializable{
// <editor-fold defaultstate="collapsed" desc="inject">

    @Inject
   com.accreditation.repository.UserViewRepository userViewRepository ;
    @Inject
   UserViewSupplier userViewSupplier ;
    @Inject
   com.accreditation.repository.IconoRepository iconoRepository ;
    @Inject
   IconoSupplier iconoSupplier ;
    @Inject
   com.accreditation.repository.TipotarjetaRepository tipotarjetaRepository ;
    @Inject
   TipotarjetaSupplier tipotarjetaSupplier ;
    @Inject
   TareaSupplier tareaSupplier ;
    @Inject
   ComentarioSupplier comentarioSupplier ;
    @Inject
   EtiquetaSupplier etiquetaSupplier ;
    @Inject
   ArchivoSupplier archivoSupplier ;
    @Inject
   ImpedimentoSupplier impedimentoSupplier ;
    @Inject
   ActionHistorySupplier actionHistorySupplier ;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Tarjeta get(Supplier<? extendsTarjeta> s, Document document, Boolean... showError) ">

    public Tarjeta get(Supplier<? extends Tarjeta> s, Document document_, Boolean... showError) {
        Tarjeta tarjeta= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
	
	 tarjeta.setIdtarjeta(document_.getLong("idtarjeta"));
	tarjeta.setTarjeta(document_.getString("tarjeta"));
	tarjeta.setDescripcion(document_.getString("descripcion"));
	// Referenced List<userView>
	 List<Document> userViewDocumentList = (List)document_.get("user");
	List<UserView> userViewList = new ArrayList<>();
	if( userViewDocumentList == null || userViewDocumentList.isEmpty()){


	}else{
		for( Document userViewDoc :userViewDocumentList){
			UserView userView = userViewSupplier.getId(UserView::new,userViewDoc);
			 Optional<UserView> userViewOptional = userViewRepository.findByPk(userView.getIduser());
			if(userViewOptional.isPresent()){
				userViewList.add(userViewOptional.get());
			}
		}
	}
	tarjeta.setUserView(userViewList);
		
	tarjeta.setFechainicial(document_.getDate("fechainicial"));
	tarjeta.setFechafinal(document_.getDate("fechafinal"));
	// @Referenced of [icono how Referenced]
	Icono icono = iconoSupplier.getId(Icono::new,(Document) document_.get("icono"));
	tarjeta.setIcono(iconoRepository.findByPk(icono.getIdicono()).get());
		
	// @Referenced of [tipotarjeta how Referenced]
	Tipotarjeta tipotarjeta = tipotarjetaSupplier.getId(Tipotarjeta::new,(Document) document_.get("tipotarjeta"));
	tarjeta.setTipotarjeta(tipotarjetaRepository.findByPk(tipotarjeta.getIdtipotarjeta()).get());
		
	tarjeta.setIdsprint(document_.getLong("idsprint"));
	tarjeta.setIdproyecto(document_.getLong("idproyecto"));
	tarjeta.setBacklog(document_.getBoolean("backlog"));
	tarjeta.setPrioridad(document_.getString("prioridad"));
	tarjeta.setEstimacion(document_.getString("estimacion"));
	tarjeta.setColumna(document_.getString("columna"));
	tarjeta.setActive(document_.getBoolean("active"));

	// Embedded List<tarea>
	List<Tarea> tareaList = new ArrayList<>();
	List<Document> tareaDoc = (List) document_.get("tarea");
	if( tareaDoc == null || tareaDoc.isEmpty()){


	}else{
		for( Document docTarea : tareaDoc){
			Tarea tarea = tareaSupplier.get(Tarea::new, docTarea);
			tareaList.add(tarea);
		}
	}
	tarjeta.setTarea(tareaList);
		

	// Embedded List<comentario>
	List<Comentario> comentarioList = new ArrayList<>();
	List<Document> comentarioDoc = (List) document_.get("comentario");
	if( comentarioDoc == null || comentarioDoc.isEmpty()){


	}else{
		for( Document docComentario : comentarioDoc){
			Comentario comentario = comentarioSupplier.get(Comentario::new, docComentario);
			comentarioList.add(comentario);
		}
	}
	tarjeta.setComentario(comentarioList);
		

	// Embedded List<etiqueta>
	List<Etiqueta> etiquetaList = new ArrayList<>();
	List<Document> etiquetaDoc = (List) document_.get("etiqueta");
	if( etiquetaDoc == null || etiquetaDoc.isEmpty()){


	}else{
		for( Document docEtiqueta : etiquetaDoc){
			Etiqueta etiqueta = etiquetaSupplier.get(Etiqueta::new, docEtiqueta);
			etiquetaList.add(etiqueta);
		}
	}
	tarjeta.setEtiqueta(etiquetaList);
		

	// Embedded List<archivo>
	List<Archivo> archivoList = new ArrayList<>();
	List<Document> archivoDoc = (List) document_.get("archivo");
	if( archivoDoc == null || archivoDoc.isEmpty()){


	}else{
		for( Document docArchivo : archivoDoc){
			Archivo archivo = archivoSupplier.get(Archivo::new, docArchivo);
			archivoList.add(archivo);
		}
	}
	tarjeta.setArchivo(archivoList);
		

	// Embedded List<impedimento>
	List<Impedimento> impedimentoList = new ArrayList<>();
	List<Document> impedimentoDoc = (List) document_.get("impedimento");
	if( impedimentoDoc == null || impedimentoDoc.isEmpty()){


	}else{
		for( Document docImpedimento : impedimentoDoc){
			Impedimento impedimento = impedimentoSupplier.get(Impedimento::new, docImpedimento);
			impedimentoList.add(impedimento);
		}
	}
	tarjeta.setImpedimento(impedimentoList);
		
	tarjeta.setForeaneo(document_.getBoolean("foreaneo"));
	tarjeta.setLastModification(document_.getDate("lastModification"));

	// Embedded List<actionHistory>
	List<ActionHistory> actionHistoryList = new ArrayList<>();
	List<Document> actionHistoryDoc = (List) document_.get("actionHistory");
	if( actionHistoryDoc == null || actionHistoryDoc.isEmpty()){


	}else{
		for( Document docActionHistory : actionHistoryDoc){
			ActionHistory actionHistory = actionHistorySupplier.get(ActionHistory::new, docActionHistory);
			actionHistoryList.add(actionHistory);
		}
	}
	tarjeta.setActionHistory(actionHistoryList);
		

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return tarjeta;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Tarjeta getId(Supplier<? extendsTarjeta> s, Document document, Boolean... showError) ">

    public Tarjeta getId(Supplier<? extends Tarjeta> s, Document document_, Boolean... showError) {
        Tarjeta tarjeta= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
	
	 tarjeta.setIdtarjeta(document_.getLong("idtarjeta"));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return tarjeta;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Tarjeta tarjeta) ">

    public Document toDocument(Tarjeta tarjeta) {
        Document document_ = new Document();
        try {
	 
		document_.put("idtarjeta",tarjeta.getIdtarjeta());
		document_.put("tarjeta",tarjeta.getTarjeta());
		document_.put("descripcion",tarjeta.getDescripcion());

	// Embedded List<userView>
		document_.put("userView",userViewSupplier.toDocument(tarjeta.getUserView()));
		document_.put("fechainicial",tarjeta.getFechainicial());
		document_.put("fechafinal",tarjeta.getFechafinal());

	// Embedded of icono
		document_.put("icono",iconoSupplier.toDocument(tarjeta.getIcono()));

	// Embedded of tipotarjeta
		document_.put("tipotarjeta",tipotarjetaSupplier.toDocument(tarjeta.getTipotarjeta()));
		document_.put("idsprint",tarjeta.getIdsprint());
		document_.put("idproyecto",tarjeta.getIdproyecto());
		document_.put("backlog",tarjeta.getBacklog());
		document_.put("prioridad",tarjeta.getPrioridad());
		document_.put("estimacion",tarjeta.getEstimacion());
		document_.put("columna",tarjeta.getColumna());
		document_.put("active",tarjeta.getActive());

	// Embedded List<tarea>
		document_.put("tarea",tareaSupplier.toDocument(tarjeta.getTarea()));

	// Embedded List<comentario>
		document_.put("comentario",comentarioSupplier.toDocument(tarjeta.getComentario()));

	// Embedded List<etiqueta>
		document_.put("etiqueta",etiquetaSupplier.toDocument(tarjeta.getEtiqueta()));

	// Embedded List<archivo>
		document_.put("archivo",archivoSupplier.toDocument(tarjeta.getArchivo()));

	// Embedded List<impedimento>
		document_.put("impedimento",impedimentoSupplier.toDocument(tarjeta.getImpedimento()));
		document_.put("foreaneo",tarjeta.getForeaneo());
		document_.put("lastModification",tarjeta.getLastModification());

	// Embedded List<actionHistory>
		document_.put("actionHistory",actionHistorySupplier.toDocument(tarjeta.getActionHistory()));
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Tarjeta> tarjetaList) ">

    public List<Document> toDocument(List<Tarjeta> tarjetaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Tarjeta tarjeta : tarjetaList){
		 Document document_ = new Document();
		document_.put("idtarjeta",tarjeta.getIdtarjeta());
		document_.put("tarjeta",tarjeta.getTarjeta());
		document_.put("descripcion",tarjeta.getDescripcion());

	// Embedded List<userView>
		document_.put("userView",userViewSupplier.toDocument(tarjeta.getUserView()));
		document_.put("fechainicial",tarjeta.getFechainicial());
		document_.put("fechafinal",tarjeta.getFechafinal());

	// Embedded of icono
		document_.put("icono",iconoSupplier.toDocument(tarjeta.getIcono()));

	// Embedded of tipotarjeta
		document_.put("tipotarjeta",tipotarjetaSupplier.toDocument(tarjeta.getTipotarjeta()));
		document_.put("idsprint",tarjeta.getIdsprint());
		document_.put("idproyecto",tarjeta.getIdproyecto());
		document_.put("backlog",tarjeta.getBacklog());
		document_.put("prioridad",tarjeta.getPrioridad());
		document_.put("estimacion",tarjeta.getEstimacion());
		document_.put("columna",tarjeta.getColumna());
		document_.put("active",tarjeta.getActive());

	// Embedded List<tarea>
		document_.put("tarea",tareaSupplier.toDocument(tarjeta.getTarea()));

	// Embedded List<comentario>
		document_.put("comentario",comentarioSupplier.toDocument(tarjeta.getComentario()));

	// Embedded List<etiqueta>
		document_.put("etiqueta",etiquetaSupplier.toDocument(tarjeta.getEtiqueta()));

	// Embedded List<archivo>
		document_.put("archivo",archivoSupplier.toDocument(tarjeta.getArchivo()));

	// Embedded List<impedimento>
		document_.put("impedimento",impedimentoSupplier.toDocument(tarjeta.getImpedimento()));
		document_.put("foreaneo",tarjeta.getForeaneo());
		document_.put("lastModification",tarjeta.getLastModification());

	// Embedded List<actionHistory>
		document_.put("actionHistory",actionHistorySupplier.toDocument(tarjeta.getActionHistory()));
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Bson toUpdate (Tarjeta tarjeta) ">

    public Bson toUpdate(Tarjeta tarjeta) {
        Bson update_ = Filters.empty();
        try {
        update_ = Updates.combine(
	 
		Updates.set("idtarjeta",tarjeta.getIdtarjeta()),
		Updates.set("tarjeta",tarjeta.getTarjeta()),
		Updates.set("descripcion",tarjeta.getDescripcion()),
 
	// ViewReferenced List<userView>
		Updates.set("user",userViewSupplier.toReferenced(tarjeta.getUserView())),
		Updates.set("fechainicial",tarjeta.getFechainicial()),
		Updates.set("fechafinal",tarjeta.getFechafinal()),
 
	// Referenced of icono
		Updates.set("icono",iconoSupplier.toReferenced(tarjeta.getIcono())),
 
	// Referenced of tipotarjeta
		Updates.set("tipotarjeta",tipotarjetaSupplier.toReferenced(tarjeta.getTipotarjeta())),
		Updates.set("idsprint",tarjeta.getIdsprint()),
		Updates.set("idproyecto",tarjeta.getIdproyecto()),
		Updates.set("backlog",tarjeta.getBacklog()),
		Updates.set("prioridad",tarjeta.getPrioridad()),
		Updates.set("estimacion",tarjeta.getEstimacion()),
		Updates.set("columna",tarjeta.getColumna()),
		Updates.set("active",tarjeta.getActive()),

	// Embedded List<tarea>
		Updates.set("tarea",tareaSupplier.toDocument(tarjeta.getTarea())),

	// Embedded List<comentario>
		Updates.set("comentario",comentarioSupplier.toDocument(tarjeta.getComentario())),

	// Embedded List<etiqueta>
		Updates.set("etiqueta",etiquetaSupplier.toDocument(tarjeta.getEtiqueta())),

	// Embedded List<archivo>
		Updates.set("archivo",archivoSupplier.toDocument(tarjeta.getArchivo())),

	// Embedded List<impedimento>
		Updates.set("impedimento",impedimentoSupplier.toDocument(tarjeta.getImpedimento())),
		Updates.set("foreaneo",tarjeta.getForeaneo()),
		Updates.set("lastModification",tarjeta.getLastModification()),

	// Embedded List<actionHistory>
		Updates.set("actionHistory",actionHistorySupplier.toDocument(tarjeta.getActionHistory()))
	

        );
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return update_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Tarjeta> tarjetaList) ">

    public List<Bson> toUpdate(List<Tarjeta> tarjetaList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
	 for(Tarjeta tarjeta : tarjetaList){
		 Bson update_ = Filters.empty();
			update_ = Updates.combine(
	 
		Updates.set("idtarjeta",tarjeta.getIdtarjeta()),
		Updates.set("tarjeta",tarjeta.getTarjeta()),
		Updates.set("descripcion",tarjeta.getDescripcion()),
 
	// ViewReferenced List<userView>
		Updates.set("user",userViewSupplier.toReferenced(tarjeta.getUserView())),
		Updates.set("fechainicial",tarjeta.getFechainicial()),
		Updates.set("fechafinal",tarjeta.getFechafinal()),
 
	// Referenced of icono
		Updates.set("icono",iconoSupplier.toReferenced(tarjeta.getIcono())),
 
	// Referenced of tipotarjeta
		Updates.set("tipotarjeta",tipotarjetaSupplier.toReferenced(tarjeta.getTipotarjeta())),
		Updates.set("idsprint",tarjeta.getIdsprint()),
		Updates.set("idproyecto",tarjeta.getIdproyecto()),
		Updates.set("backlog",tarjeta.getBacklog()),
		Updates.set("prioridad",tarjeta.getPrioridad()),
		Updates.set("estimacion",tarjeta.getEstimacion()),
		Updates.set("columna",tarjeta.getColumna()),
		Updates.set("active",tarjeta.getActive()),

	// Embedded List<tarea>
		Updates.set("tarea",tareaSupplier.toDocument(tarjeta.getTarea())),

	// Embedded List<comentario>
		Updates.set("comentario",comentarioSupplier.toDocument(tarjeta.getComentario())),

	// Embedded List<etiqueta>
		Updates.set("etiqueta",etiquetaSupplier.toDocument(tarjeta.getEtiqueta())),

	// Embedded List<archivo>
		Updates.set("archivo",archivoSupplier.toDocument(tarjeta.getArchivo())),

	// Embedded List<impedimento>
		Updates.set("impedimento",impedimentoSupplier.toDocument(tarjeta.getImpedimento())),
		Updates.set("foreaneo",tarjeta.getForeaneo()),
		Updates.set("lastModification",tarjeta.getLastModification()),

	// Embedded List<actionHistory>
		Updates.set("actionHistory",actionHistorySupplier.toDocument(tarjeta.getActionHistory()))
	

        );
		bsonList_.add(update_);
 
       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return bsonList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Tarjeta tarjeta) ">

    public Document toReferenced(Tarjeta tarjeta) {
        Document document_ = new Document();
        try {
	 
		document_.put("idtarjeta",tarjeta.getIdtarjeta());
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced(List<Tarjeta> tarjetaList) ">

    public List<Document> toReferenced(List<Tarjeta> tarjetaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Tarjeta tarjeta : tarjetaList){
		 Document document_ = new Document();
		document_.put("idtarjeta",tarjeta.getIdtarjeta());
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>

}