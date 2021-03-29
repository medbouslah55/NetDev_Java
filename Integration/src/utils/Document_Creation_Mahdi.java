/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Evenement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import services.ServicePublication;

/**
 *
 * @author benra
 */
public class Document_Creation_Mahdi {
    
    public void generatePDF() throws IOException, SQLException {
        PDDocument doc = new PDDocument();
        
        ServicePublication ar = new ServicePublication();
        ArrayList<Evenement> articles = new ArrayList<>();

        // ArrayList<Article> list=articles.toArray();
        articles = (ArrayList<Evenement>) ar.afficherPDF();

        //  Set<Consultation> list_cons =(Set<Consultation>) ms.getAllCons(id);
        //     membres = ms.getAllUsers1(idd);
        //      System.out.println(membres);
        PDPage page = new PDPage();
        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        //  PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\\\Users\\\\SAIFOUN\\\\Desktop\\\\annonce\\\\article1\\\\article1\\\\src\\\\images\\\\lazy.jpg", doc);
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\trabe\\Desktop\\Integration\\src\\images\\Logo.png", doc);
        contentStream.drawXObject(pdImage, 50, 605, 140, 140);

        ///////////////////////////////////////////////////////////////////////////////////////////////
        String operater_name = "MindSpace";
        PDFont font = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font, 24);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(220, 700);
        contentStream.showText(operater_name);
        contentStream.endText();

        String adress = "Avenue Habib Bourguiba, Tunis 1000";
        contentStream.beginText();
        PDFont font3 = PDType1Font.COURIER_BOLD;
        contentStream.setFont(font3, 12);
        contentStream.newLineAtOffset(220, 670);
        contentStream.showText(adress);
        contentStream.endText();

        String operater_phone_number = "50 450 360";
        contentStream.beginText();
        contentStream.setFont(font3, 12);
        contentStream.newLineAtOffset(220, 650);
        contentStream.showText(operater_phone_number);
        contentStream.endText();

        String operater_email = "www.MindSpace.tn";
        contentStream.beginText();
        contentStream.setFont(font3, 12);
        contentStream.newLineAtOffset(220, 630);
        contentStream.showText(operater_email);
        contentStream.endText();

        ///////////////////////////////////////////////////////////////////////////////////////////////
        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        PDFont font4 = PDType1Font.HELVETICA_BOLD_OBLIQUE;
        contentStream.setFont(font4, 30);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 550); //580
        contentStream.showText("Evenement");
        contentStream.endText();

        //////////////////////////////////Client Left //////////////////////////////////////////////////
        //   contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
        //  contentStream.addRect(50, 420, 190, 80); //330
        // contentStream.fill();
        //////////////////////////////////Client Right //////////////////////////////////////////////////
        //   contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
        // contentStream.addRect(300, 420, 250, 80); //330
        // contentStream.fill();
        ////////////// IMAGE BOX //////////////////
        contentStream.addLine(50, 730, 560, 730);
        contentStream.fill();
        contentStream.addLine(50, 610, 50, 730);
        contentStream.fill();
        contentStream.addLine(50, 610, 560, 610);
        contentStream.fill();
        contentStream.addLine(560, 610, 560, 730);
        contentStream.fill();

        ///////////////////////////////////
        contentStream.addLine(30, 30, 580, 30);
        contentStream.fill();
        contentStream.addLine(30, 30, 30, 750);
        contentStream.fill();
        contentStream.addLine(30, 750, 580, 750);
        contentStream.fill();
        contentStream.addLine(580, 750, 580, 30);

        contentStream.fill();

        drawTable(page, contentStream, 365, 50, articles);

        contentStream.setNonStrokingColor(Color.BLUE);
        String Contactus = "Contact Us :";
        PDFont font5 = PDType1Font.COURIER_BOLD;
        contentStream.beginText();
        contentStream.setFont(font3, 12);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 130);
        contentStream.showText(Contactus);
        contentStream.endText();

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        contentStream.setNonStrokingColor(Color.black);
        String Parg = "If there are any questions regarding this privacy & security policy you may contact us using the information below :";
        contentStream.beginText();
        contentStream.setFont(font3, 7);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 110);
        contentStream.showText(Parg);
        contentStream.endText();

        String Parg2 = "www.MindSpace.tn";
        contentStream.beginText();
        contentStream.setFont(font3, 8);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 95);
        contentStream.showText(Parg2);
        contentStream.endText();

        String Parg3 = "Avenue Habib Bourguiba, Tunis 1000";
        contentStream.beginText();
        contentStream.setFont(font3, 8);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 80);
        contentStream.showText(Parg3);
        contentStream.endText();

        String Parg4 = "E-Mail ID:mindspace@mindspace.tn";
        contentStream.beginText();
        contentStream.setFont(font3, 9);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 65);
        contentStream.showText(Parg4);
        contentStream.endText();

        contentStream.close();

        System.out.println(articles);

        doc.save("my_doc.pdf");
        doc.close();
        System.out.println("pdf saved");

    }

    public static void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, ArrayList<Evenement> articles)
            throws IOException {

        final int rows = articles.size() + 1;
        final int cols = 3;
        final float rowHeight = 20f;
        final float tableWidth = 517;
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth / (float) cols;
        final float cellMargin = 5f;

        PDFont font3 = PDType1Font.COURIER_BOLD;
        contentStream.setNonStrokingColor(Color.black);
        String client = "Evenement";
        PDFont font5 = PDType1Font.TIMES_BOLD_ITALIC;
        contentStream.beginText();
        contentStream.setFont(font3, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(52, 375);
        contentStream.showText(client);
        contentStream.endText();

        //draw the rows
        float nexty = y;
        for (int i = 0; i <= rows; i++) {

            contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
            nexty -= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(nextx, y, nextx, y - tableHeight);
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);

        float textx = margin + cellMargin;
        float texty = y - 15;

        String[] list = {"id", "Date_Pub", "Date_even"};

        contentStream.setNonStrokingColor(Color.RED);
        for (int i = 0; i < list.length; i++) {
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx, texty);
            contentStream.drawString(list[i]);
            contentStream.endText();
            textx += colWidth;
        }
        texty -= rowHeight;
        textx = margin + cellMargin;
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        contentStream.setNonStrokingColor(Color.black);
        for (Evenement cons : articles) {

            String text;
            if (cons.getSujet().length() > 14) {
                text = cons.getSujet().substring(0, 13) + "..";
            } else {
                text = cons.getSujet();
            }

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx, texty);
            contentStream.drawString(text);
            contentStream.endText();
            textx += colWidth;
            //////////////////////
            String text2 = "" + cons.getDate_even();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx, texty);
            contentStream.drawString(text2);
            contentStream.endText();
            textx += colWidth;
            //////////////////////
            String text3 = "" + cons.getDate_pub();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(textx, texty);
            contentStream.drawString(text3);
            contentStream.endText();
            textx += colWidth;
//            //////////////////////
//            String text4 = "" + cons.getDejeuner();
//            contentStream.beginText();
//            contentStream.moveTextPositionByAmount(textx, texty);
//            contentStream.drawString(text4);
//            contentStream.endText();
//            textx += colWidth;
//            //////////////////////
//            String text5 = "" + cons.getDejeuner();
//            contentStream.beginText();
//            contentStream.moveTextPositionByAmount(textx, texty);
//            contentStream.drawString(text5);
//            contentStream.endText();
//            textx += colWidth;
//            //////////////////////
//            String text6 = "" + cons.getDejeuner();
//            contentStream.beginText();
//            contentStream.moveTextPositionByAmount(textx, texty);
//            contentStream.drawString(text6);
//            contentStream.endText();
//            textx += colWidth;
//            //////////////////////
//            String text7 = "" + cons.getTotal_calories();
//            contentStream.beginText();
//            contentStream.moveTextPositionByAmount(textx, texty);
//            contentStream.drawString(text7);
//            contentStream.endText();
//            textx += colWidth;
            //////////////////////

            texty -= rowHeight;
            textx = margin + cellMargin;
        }

    }

//    public static void drawContaners(PDPage page, PDPageContentStream contentStream, ArrayList <Membre> membres) throws IOException {
//		
//        MembreService ms = new MembreService();
//        for(Membre memb: membres) {
//            contentStream.setNonStrokingColor(Color.black);
//            PDFont font3 = PDType1Font.COURIER_BOLD;
//	      String Reference="Animal Code : "+memb.getId();
//	      contentStream.beginText();
//	      contentStream.setFont(font3, 12);
//	      contentStream.setLeading(14.5f);
//	      contentStream.newLineAtOffset(55, 390); //520
//	      contentStream.showText(Reference);
//	      contentStream.endText();
//	      
//	      
////	      String Date="E Mail      : "+memb.getEmail();
////	      contentStream.beginText();
////	      contentStream.setFont(font3, 12);
////	      contentStream.setLeading(14.5f);
////	      contentStream.newLineAtOffset(55, 370);
////	      contentStream.showText(Date);
////	      contentStream.endText();
//	      
//	      
//	      String Id_client="Phone N°  : "+memb.getPhone();
//	      contentStream.beginText();
//	      contentStream.setFont(font3, 12);
//	      contentStream.setLeading(14.5f);
//	      contentStream.newLineAtOffset(55, 350);
//	      contentStream.showText(Id_client);
//	      contentStream.endText();
//        
//        
//            ///////////////////////////////////////////////////////////////////////////////////////////////
//			
//			
//			contentStream.setNonStrokingColor(Color.black);
//			String client="Client Information:";
//			PDFont font5 = PDType1Font.TIMES_BOLD_ITALIC;
//			contentStream.beginText();
//			contentStream.setFont(font3, 15);
//			contentStream.setLeading(14.5f);
//			contentStream.newLineAtOffset(52, 418);
//			contentStream.showText(client);
//			contentStream.endText();
//			
//			String name_Client="First Name : "+memb.getPrenom();
//			contentStream.beginText();
//			contentStream.setFont(font3, 12);
//			contentStream.setLeading(14.5f);
//			contentStream.newLineAtOffset(303, 390);
//			contentStream.showText(name_Client);
//			contentStream.endText();
//			
//			
//			String lastName_client="Last Name : "+memb.getNom();
//			contentStream.beginText();
//			contentStream.setFont(font3, 12);
//			contentStream.setLeading(14.5f);
//			contentStream.newLineAtOffset(303, 370);
//			contentStream.showText(lastName_client);
//			contentStream.endText();
//			
//			
//			String adress_client="Adress : "+memb.getAdress();
//			contentStream.beginText();
//			contentStream.setFont(font3, 12);
//			contentStream.setLeading(14.5f);
//			contentStream.newLineAtOffset(303, 350);
//			contentStream.showText(adress_client);
//			contentStream.endText();  
//			
//                }
//        
//		}
    /*
public static void drawContaners2(PDPage page, PDPageContentStream contentStream, ArrayList <Animal> animals) throws IOException {
		
        MembreService ms = new MembreService();
        for(Animal memb: animals) {
            contentStream.setNonStrokingColor(Color.black);
            PDFont font3 = PDType1Font.COURIER_BOLD;
	      String Reference="Animal Code : "+memb.getId();
	      contentStream.beginText();
	      contentStream.setFont(font3, 12);
	      contentStream.setLeading(14.5f);
	      contentStream.newLineAtOffset(55, 480); //520
	      contentStream.showText(Reference);
	      contentStream.endText();
	      
	      
//	      String Date="E Mail      : "+memb.getEmail();
//	      contentStream.beginText();
//	      contentStream.setFont(font3, 12);
//	      contentStream.setLeading(14.5f);
//	      contentStream.newLineAtOffset(55, 370);
//	      contentStream.showText(Date);
//	      contentStream.endText();
	      
	      
	      String Id_client="Birthdate: "+memb.getBirthdate();
	      contentStream.beginText();
	      contentStream.setFont(font3, 12);
	      contentStream.setLeading(14.5f);
	      contentStream.newLineAtOffset(55, 440);
	      contentStream.showText(Id_client);
	      contentStream.endText();
        
        
            ///////////////////////////////////////////////////////////////////////////////////////////////
			
			
			contentStream.setNonStrokingColor(Color.black);
			String client="Animal Informations :";
			PDFont font5 = PDType1Font.TIMES_BOLD_ITALIC;
			contentStream.beginText();
			contentStream.setFont(font3, 15);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(52, 508);
			contentStream.showText(client);
			contentStream.endText();
			
			String name_Client="Name : "+memb.getNom();
			contentStream.beginText();
			contentStream.setFont(font3, 12);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(303, 480);
			contentStream.showText(name_Client);
			contentStream.endText();
			
			
			String lastName_client="Gender : "+memb.getGender();
			contentStream.beginText();
			contentStream.setFont(font3, 12);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(303, 460);
			contentStream.showText(lastName_client);
			contentStream.endText();
			
			
			String adress_client="Race : "+memb.getRace();
			contentStream.beginText();
			contentStream.setFont(font3, 12);
			contentStream.setLeading(14.5f);
			contentStream.newLineAtOffset(303, 440);
			contentStream.showText(adress_client);
			contentStream.endText();  
			
                }
        
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//public void generatePDF2() throws IOException, SQLException{
//        
//         
//        AchatService as = new AchatService();
////            
//        ObservableList<animal2> list2 = as.read();
////        ArrayList<Membre> membres = new ArrayList<>();
////        ArrayList<Animal> animals = new ArrayList<>();
////        
//          
////        
////      //  Set<Consultation> list_cons =(Set<Consultation>) ms.getAllCons(id);
////		 
////       //     membres = ms.getAllUsers1(idd);
////            animals = ms.getAllAnimals(idd);
//            
//          //      System.out.println(membres);
//                
//        PDDocument doc = new PDDocument();
//        PDPage page = new PDPage();
//        doc.addPage(page);
//        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//                                
//        
//              PDImageXObject pdImage = PDImageXObject.createFromFile("src/main/java/GUI1/logo.png", doc);
//	      contentStream.drawXObject(pdImage, 50, 605, 140, 140);
//              
//	      
//	      ///////////////////////////////////////////////////////////////////////////////////////////////
//	      String operater_name="PETS WORLD";
//	      PDFont font = PDType1Font.TIMES_ROMAN;
//	      contentStream.beginText();
//	      contentStream.setFont(font, 24);
//	      contentStream.setLeading(14.5f);
//	      contentStream.newLineAtOffset(220, 700);
//	      contentStream.showText(operater_name);
//	      contentStream.endText();
//	      
//	      String adress="Avenue Habib Bourguiba, Tunis 1000";
//	      contentStream.beginText();
//	      PDFont font3 = PDType1Font.COURIER_BOLD;
//	      contentStream.setFont(font3, 12);
//	      contentStream.newLineAtOffset(220, 670);
//	      contentStream.showText(adress);
//	      contentStream.endText();
//	      
//	      String operater_phone_number="50 450 360";
//	      contentStream.beginText();
//	      contentStream.setFont(font3, 12);
//	      contentStream.newLineAtOffset(220, 650);
//	      contentStream.showText(operater_phone_number);
//	      contentStream.endText();
//	      
//	      String operater_email="www.petsworld.tn";
//	      contentStream.beginText();
//	      contentStream.setFont(font3, 12);
//	      contentStream.newLineAtOffset(220, 630);
//	      contentStream.showText(operater_email);
//	      contentStream.endText();
//	      
//			///////////////////////////////////////////////////////////////////////////////////////////////
//			contentStream.beginText();
//			contentStream.setNonStrokingColor(Color.black);
//			PDFont font4 = PDType1Font.HELVETICA_BOLD_OBLIQUE;
//			contentStream.setFont(font4, 30);
//			contentStream.setLeading(14.5f);
//			contentStream.newLineAtOffset(100, 520); //580
//			contentStream.showText("PRODUCT LIST");
//			contentStream.endText();
//	      
//	      //////////////////////////////////Client Left //////////////////////////////////////////////////
////                      contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
////                      contentStream.addRect(50, 330, 190, 80);
////                      contentStream.fill();
//              //////////////////////////////////Client Right //////////////////////////////////////////////////
////                    contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
////                    contentStream.addRect(300, 330, 250, 80);
////                    contentStream.fill();
//			
//              ////////////// IMAGE BOX //////////////////
//              
//                    contentStream.addLine(50, 730, 560, 730);
//		    contentStream.fill();
//		    contentStream.addLine(50, 610, 50, 730);
//		    contentStream.fill();
//		    contentStream.addLine(50, 610, 560, 610);
//		    contentStream.fill();
//		    contentStream.addLine(560, 610, 560, 730);
//		    contentStream.fill();
//                         
//              ///////////////////////////////////
//              
//                    contentStream.addLine(30, 30, 580,30);
//		    contentStream.fill();
//		    contentStream.addLine(30, 30, 30,750);
//		    contentStream.fill();
//		    contentStream.addLine(30, 750, 580, 750);
//		    contentStream.fill();
//		    contentStream.addLine(580, 750, 580, 30);
//		    contentStream.fill();
//                        
//                           drawTable3(page, contentStream, 430, 50, list2);
////              //drawContaners(page, contentStream, membres);
////              drawContaners2(page, contentStream, animals);
//      contentStream.close();
////              
////              System.out.println(animals);
//              
//          doc.save("my_doc.pdf");
//          doc.close();
//          System.out.println("pdf saved");
//
//
//    }
//    
//    public static void drawTable3(PDPage page, PDPageContentStream contentStream,float y, float margin, ObservableList<animal2> list2) 
//			throws IOException {
//		
//		final int rows = list2.size()+1;
//		final int cols = 3;
//		final float rowHeight = 20f;
//		final float tableWidth = 517;
//		final float tableHeight = rowHeight * rows;
//		final float colWidth = tableWidth/(float)cols;
//		final float cellMargin=5f;
//		
//		//draw the rows
//		float nexty = y ;
//		for (int i = 0; i <= rows; i++) {
//                
//		contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
//		nexty-= rowHeight;
//		}
//		
//		//draw the columns
//		float nextx = margin;
//		for (int i = 0; i <= cols; i++) {
//		contentStream.drawLine(nextx,y,nextx,y-tableHeight);
//		nextx += colWidth;
//		}
//		
//		//now add the text
//		contentStream.setFont(PDType1Font.HELVETICA_BOLD,11);
//		
//		float textx = margin+cellMargin;
//		float texty = y-15;
//		
//		String[] list = {"Anim","Prix", "Description"};
//		
//		contentStream.setNonStrokingColor(Color.RED);
//		for(int i=0; i<list.length; i++){
//			contentStream.beginText();
//			contentStream.moveTextPositionByAmount(textx,texty);
//			contentStream.drawString(list[i]);
//			contentStream.endText();
//			textx += colWidth;
//		}
//		texty-=rowHeight;
//		textx = margin+cellMargin;
//                contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);
//		contentStream.setNonStrokingColor(Color.black);
//		for(animal2 cons: list2) {
//			
//				
//				String text;
//				if(cons.getAnim().length()>14)
//					text = cons.getAnim().substring(0, 13)+"..";
//				else text=cons.getAnim();
//						
//				contentStream.beginText();
//				contentStream.moveTextPositionByAmount(textx,texty);
//				contentStream.drawString(text);
//				contentStream.endText();
//				textx += colWidth;
//				//////////////////////
//				String text2 = ""+cons.getPrix();
//				contentStream.beginText();
//				contentStream.moveTextPositionByAmount(textx,texty);
//				contentStream.drawString(text2);
//				contentStream.endText();
//				textx += colWidth;
//				//////////////////////
//				String text3 = ""+cons.getDescription();
//				contentStream.beginText();
//				contentStream.moveTextPositionByAmount(textx,texty);
//				contentStream.drawString(text3);
//				contentStream.endText();
//				textx += colWidth;
//				//////////////////////
//
//			
//			texty-=rowHeight;
//			textx = margin+cellMargin;
//		}
//		*/
//
//	
//	}
}