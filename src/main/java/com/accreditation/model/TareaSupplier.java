package com.accreditation.model;
// <editor-fold defaultstate="collapsed" desc="imports">

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
/**
* Java
*/
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
/**
* Jmoordb
*/
import com.jmoordb.core.util.MessagesUtil;
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
import com.accreditation.model.Tarea;
import com.accreditation.model.*;


// </editor-fold>
@RequestScoped
public class TareaSupplier  implements Serializable{
// <editor-fold defaultstate="collapsed" desc="inject">

    @Inject
   com.accreditation.repository.UserViewRepository userViewRepository ;
    @Inject
   UserViewSupplier userViewSupplier ;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Tarea get(Supplier<? extendsTarea> s, Document document, Boolean... showError) ">

    public Tarea get(Supplier<? extends Tarea> s, Document document_, Boolean... showError) {
        Tarea tarea= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
		tarea.setTarea(document_.getString("tarea"));
	tarea.setCompletado(document_.getBoolean("completado"));
	tarea.setActive(document_.getBoolean("active"));
	tarea.setOrden(document_.getInteger("orden"));
	// @Referenced of [userView how Referenced]
	UserView userView = userViewSupplier.getId(UserView::new,(Document) document_.get("user"));
	tarea.setUserView(userViewRepository.findByPk(userView.getIduser()).get());
		document_.put("user",userViewSupplier.toDocument(tarea.getUserView()));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return tarea;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Tarea get(Supplier<? extendsTarea> s, Document document, Boolean... showError) ">

    public Tarea getId(Supplier<? extends Tarea> s, Document document_, Boolean... showError) {
        Tarea tarea= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
	
         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return tarea;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Tarea tarea) ">

    public Document toDocument(Tarea tarea) {
        Document document_ = new Document();
        try {
	 		document_.put("tarea",tarea.getTarea());
		document_.put("completado",tarea.getCompletado());
		document_.put("active",tarea.getActive());
		document_.put("orden",tarea.getOrden());

	// Embedded of userView
		document_.put("userView",userViewSupplier.toDocument(tarea.getUserView()));
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Tarea> tareaList) ">

    public List<Document> toDocument(List<Tarea> tareaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Tarea tarea : tareaList){
		 Document document_ = new Document();
		document_.put("tarea",tarea.getTarea());
		document_.put("completado",tarea.getCompletado());
		document_.put("active",tarea.getActive());
		document_.put("orden",tarea.getOrden());

	// Embedded of userView
		document_.put("userView",userViewSupplier.toDocument(tarea.getUserView()));
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Bson toUpdate (Tarea tarea) ">

    public Bson toUpdate(Tarea tarea) {
       Bson update_ = Filters.empty();
        try {
        update_ = Updates.combine(
	 
		Updates.set("tarea",tarea.getTarea()),
		Updates.set("completado",tarea.getCompletado()),
		Updates.set("active",tarea.getActive()),
		Updates.set("orden",tarea.getOrden()),
 
	// Referenced of userView
		Updates.set("user",userViewSupplier.toReferenced(tarea.getUserView()))
	

        );
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return update_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Tarea> tareaList) ">

    public List<Bson> toUpdate(List<Tarea> tareaList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
	 for(Tarea tarea : tareaList){
		 Bson update_ = Filters.empty();
			update_ = Updates.combine(
	 
		Updates.set("tarea",tarea.getTarea()),
		Updates.set("completado",tarea.getCompletado()),
		Updates.set("active",tarea.getActive()),
		Updates.set("orden",tarea.getOrden()),
 
	// Referenced of userView
		Updates.set("user",userViewSupplier.toReferenced(tarea.getUserView()))
	

        );
		bsonList_.add(update_);
 
       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return bsonList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Tarea tarea) ">

    public Document toReferenced(Tarea tarea) {
        Document document_ = new Document();
        try {
	 	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced (List<Tarea> tareaList) ">

    public List<Document> toReferenced(List<Tarea> tareaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Tarea tarea : tareaList){
		 Document document_ = new Document();
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>

}