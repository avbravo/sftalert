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
import com.accreditation.model.Etiqueta;
import com.accreditation.model.*;


// </editor-fold>
@RequestScoped
public class EtiquetaSupplier  implements Serializable{
// <editor-fold defaultstate="collapsed" desc=" public Etiqueta get(Supplier<? extendsEtiqueta> s, Document document, Boolean... showError) ">

    public Etiqueta get(Supplier<? extends Etiqueta> s, Document document_, Boolean... showError) {
        Etiqueta etiqueta= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
		etiqueta.setEtiqueta(document_.getString("etiqueta"));
	etiqueta.setActive(document_.getBoolean("active"));
	etiqueta.setSeverity(document_.getString("severity"));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return etiqueta;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Etiqueta get(Supplier<? extendsEtiqueta> s, Document document, Boolean... showError) ">

    public Etiqueta getId(Supplier<? extends Etiqueta> s, Document document_, Boolean... showError) {
        Etiqueta etiqueta= s.get(); 
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
         return etiqueta;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Etiqueta etiqueta) ">

    public Document toDocument(Etiqueta etiqueta) {
        Document document_ = new Document();
        try {
	 		document_.put("etiqueta",etiqueta.getEtiqueta());
		document_.put("active",etiqueta.getActive());
		document_.put("severity",etiqueta.getSeverity());
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Etiqueta> etiquetaList) ">

    public List<Document> toDocument(List<Etiqueta> etiquetaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Etiqueta etiqueta : etiquetaList){
		 Document document_ = new Document();
		document_.put("etiqueta",etiqueta.getEtiqueta());
		document_.put("active",etiqueta.getActive());
		document_.put("severity",etiqueta.getSeverity());
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Etiqueta> etiquetaList) ">

    public List<Bson> toUpdate(List<Etiqueta> etiquetaList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
	 for(Etiqueta etiqueta : etiquetaList){
		 Bson update_ = Filters.empty();
			update_ = Updates.combine(
	 
		Updates.set("etiqueta",etiqueta.getEtiqueta()),
		Updates.set("active",etiqueta.getActive()),
		Updates.set("severity",etiqueta.getSeverity())
	

        );
		bsonList_.add(update_);
 
       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return bsonList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Etiqueta etiqueta) ">

    public Document toReferenced(Etiqueta etiqueta) {
        Document document_ = new Document();
        try {
	 	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced (List<Etiqueta> etiquetaList) ">

    public List<Document> toReferenced(List<Etiqueta> etiquetaList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Etiqueta etiqueta : etiquetaList){
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