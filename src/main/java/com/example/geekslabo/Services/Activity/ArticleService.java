package com.example.geekslabo.Services.Activity;

 import com.example.geekslabo.Entities.Claim;
 import com.google.zxing.BarcodeFormat;
 import com.google.zxing.EncodeHintType;
 import com.google.zxing.common.BitMatrix;
 import com.google.zxing.qrcode.QRCodeWriter;
 import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
import com.example.geekslabo.Entities.Article;
import com.example.geekslabo.IServices.IServiceActivities.IArticleService;
import com.example.geekslabo.Repositories.ActivityRepo.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

 import javax.imageio.ImageIO;
 import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
 import javax.validation.Valid;
 import javax.validation.Validator;
 import java.awt.*;
 import java.awt.image.BufferedImage;
 import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
 import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleService {


    ArticleRepository rep;

    @Override
    public List<Article> GetallArticle () {
        return rep.findAll ();
    }

    @Override
    public Article AddArticle (Article article) {
        return rep.save (article);
    }

    @Override
    public void delete (Integer id) {
        rep.deleteById (id);
    }

    @Override
    public Article findArticlebyId (Integer id) {
        return rep.findById (id).orElse (null);
    }

    @Override

    public Article updateArticle (Integer id , Article article) {

        Article oldArticle = rep.findById (id).orElse (null);
        if (oldArticle == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND , "article not found with id " + id);
        }
        oldArticle.setDescription (oldArticle.getDescription ());
        return rep.save (oldArticle);
    }


    public Article createArticle (@Valid MultipartFile image ,@Valid String description , @Valid String contenu) throws IOException {
        Article article = new Article ();
        article.setDescription (description);
        article.setContenu (contenu);
        Path directory = Paths.get ("path/to/save/image");
        Files.createDirectories (directory);
        Path path = directory.resolve (image.getOriginalFilename ());
        Files.write (path , image.getBytes ());
        article.setImage (image.getOriginalFilename ());
        Article savedArticle = rep.save (article);
        return savedArticle;
    }

    @Override
    public String summarizeArticle (Integer articleid) {
        // create the CoreNLP pipeline
        Properties props = new Properties ();
        props.setProperty ("annotators" , "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,sentiment");
        props.setProperty ("coref.algorithm" , "neural");
        props.setProperty ("ner.applyFineGrained" , "false");
        StanfordCoreNLP pipeline = new StanfordCoreNLP (props);

        Article article = rep.findById (articleid).orElse (null);
        if (article == null) {
            throw new ResponseStatusException (HttpStatus.NOT_FOUND , "article not found with id " + articleid);
        }

        // get the content of the article
        String articleContent = article.getContenu ();

        // create a new Annotation object with the article content
        Annotation document = new Annotation (articleContent);
        // annotate the document using the pipeline
        pipeline.annotate (document);

        // get the list of sentences in the document
        List<CoreMap> sentences = document.get (CoreAnnotations.SentencesAnnotation.class);

        // calculate the score for each sentence and store it in a map
        Map<CoreMap, Double> sentenceScores = new HashMap<> ();
        for (CoreMap sentence : sentences) {
            double score = 0;
            List<CoreLabel> tokens = sentence.get (CoreAnnotations.TokensAnnotation.class);
            for (CoreMap token : tokens) {
                String ner = token.get (CoreAnnotations.NamedEntityTagAnnotation.class);
                if (ner != null && (ner.equals ("PERSON") || ner.equals ("ORGANIZATION") || ner.equals ("LOCATION"))) {
                    score += 0.5;
                }
                String pos = token.get (CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos != null && pos.startsWith ("VB")) {
                    score += 0.5;
                }
            }
            sentenceScores.put (sentence , score);
        }

        // sort the sentences by score in descending order
        List<Map.Entry<CoreMap, Double>> sortedSentences = new ArrayList<> (sentenceScores.entrySet ());
        Collections.sort (sortedSentences , new Comparator<Map.Entry<CoreMap, Double>> () {
            public int compare (Map.Entry<CoreMap, Double> entry1 , Map.Entry<CoreMap, Double> entry2) {
                return -Double.compare (entry1.getValue () , entry2.getValue ());
            }
        });

        // build the summary by selecting the top sentences
        StringBuilder summaryBuilder = new StringBuilder ();
        int summaryLength = 0;
        for (Map.Entry<CoreMap, Double> entry : sortedSentences) {
            CoreMap sentence = entry.getKey ();
            String sentenceText = sentence.get (CoreAnnotations.TextAnnotation.class);
            if (summaryLength + sentenceText.length () > 1000) {
                break;
            }
            summaryBuilder.append (sentenceText).append (" ");
            summaryLength += sentenceText.length ();
        }
        String summary = summaryBuilder.toString ().trim ();
        article.setSummary (summary);
        rep.save (article);
        return article.getSummary ();
    }

    public void generateQRCode(Integer id){
        Article article = rep.findById(id).orElse (null);
        try {
            String qrCodeData = "Article ID: " + id + "\n" + "Description: " + article.getDescription () + "\n" + "Contenu: " + article.getContenu ();
            String filePath = "C:\\Users\\firas\\Downloads\\" + id + ".png";
            int size = 250;
            String fileType = "png";
            File qrFile = new File(filePath);

            Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, size, size, hintMap);
            int width = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, fileType, qrFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

