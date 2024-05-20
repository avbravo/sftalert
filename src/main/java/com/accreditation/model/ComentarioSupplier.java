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
import com.accreditation.model.Comentario;
import com.accreditation.model.*;


// </editor-fold>
@RequestScoped
public class ComentarioSupplier  implements Serializable{
// <editor-fold defaultstate="collapsed" desc="inject">

    @Inject
   com.accreditation.repository.UserViewRepository userViewRepository ;
    @Inject
   UserViewSupplier userViewSupplier ;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Comentario get(Supplier<? extendsComentario> s, Document document, Boolean... showError) ">

    public Comentario get(Supplier<? extends Comentario> s, Document document_, Boolean... showError) {
        Comentario comentario= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
		comentario.setComentario(document_.getString("comentario"));
	comentario.setPrivado(document_.getBoolean("privado"));
	comentario.setFecha(document_.getDate("fecha"));
	comentario.setActive(document_.getBoolean("active"));
	// @Referenced of [userView how Referenced]
	UserView userView = userViewSupplier.getId(UserView::new,(Document) document_.get("user"));
	comentario.setUserView(userViewRepository.findByPk(userView.getIduser()).get());
		document_.put("user",userViewSupplier.toDocument(comentario.getUserView()));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return comentario;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Comentario get(Supplier<? extendsComentario> s, Document document, Boolean... showError) ">

    public Comentario getId(Supplier<? extends Comentario> s, Document document_, Boolean... showError) {
        Comentario comentario= s.get(); 
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
         return comentario;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Comentario comentario) ">

    public Document toDocument(Comentario comentario) {
        Document document_ = new Document();
        try {
	 		document_.put("comentario",comentario.getComentario());
		document_.put("privado",comentario.getPrivado());
		document_.put("fecha",comentario.getFecha());
		document_.put("active",comentario.getActive());

	// Embedded of userView
		document_.put("userView",userViewSupplier.toDocument(comentario.getUserView()));
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Comentario> comentarioList) ">

    public List<Document> toDocument(List<Comentario> comentarioList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Comentario comentario : comentarioList){
		 Document document_ = new Document();
		document_.put("comentario",comentario.getComentario());
		document_.put("privado",comentario.getPrivado());
		document_.put("fecha",comentario.getFecha());
		document_.put("active",comentario.getActive());

	// Embedded of userView
		document_.put("userView",userViewSupplier.toDocument(comentario.getUserView()));
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Bson toUpdate (Comentario comentario) ">

    public Bson toUpdate(Comentario comentario) {
       Bson update_ = Filters.empty();
        try {
        update_ = Updates.combine(
	 
		Updates.set("comentario",comentario.getComentario()),
		Updates.set("privado",comentario.getPrivado()),
		Updates.set("fecha",comentario.getFecha()),
		Updates.set("active",comentario.getActive()),
 
	// Referenced of userView
		Updates.set("user",userViewSupplier.toReferenced(comentario.getUserView()))
	

        );
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return update_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Comentario> comentarioList) ">

    public List<Bson> toUpdate(List<Comentario> comentarioList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
	 for(Comentario comentario : comentarioList){
		 Bson update_ = Filters.empty();
			update_ = Updates.combine(
	 
		Updates.set("comentario",comentario.getComentario()),
		Updates.set("privado",comentario.getPrivado()),
		Updates.set("fecha",comentario.getFecha()),
		Updates.set("active",comentario.getActive()),
 
	// Referenced of userView
		Updates.set("user",userViewSupplier.toReferenced(comentario.getUserView()))
	

        );
		bsonList_.add(update_);
 
       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return bsonList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Comentario comentario) ">

    public Document toReferenced(Comentario comentario) {
        Document document_ = new Document();
        try {
	 	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced (List<Comentario> comentarioList) ">

    public List<Document> toReferenced(List<Comentario> comentarioList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Comentario comentario : comentarioList){
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