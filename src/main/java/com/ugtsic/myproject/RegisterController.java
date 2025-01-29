package com.ugtsic.myproject;

import com.ugtsic.myproject.model.Application;
import com.ugtsic.myproject.service.ApplicationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class RegisterController {

    private final ApplicationService service;
    private final JavaMailSenderImpl mailSender;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public RegisterController(ApplicationService service, JavaMailSenderImpl mailSender) {
        this.service = service;
        this.mailSender = mailSender;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }

    @PostMapping("/submit")
    public String submitApplication(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("role") String role,
            @RequestParam("scholarship") String education,
            @RequestParam(value = "observations", required = false) String observations,
            @RequestParam("resume") MultipartFile resume,
            HttpServletRequest request) throws MessagingException {

        String resumePath = saveResume(resume);

        String ipAddress = request.getRemoteAddr();

        Application application = new Application();
        application.setName(name);
        application.setEmail(email);
        application.setPhone(phone);
        application.setDesiredRole(role);
        application.setEducation(education);
        application.setObservations(observations);
        application.setIpAddress(ipAddress);
        application.setResumePath(resumePath);

        this.service.saveApplication(application);

        sendEmail(application);

        return "redirect:/success";
    }

    private String saveResume(MultipartFile file) {
        try {
            String uploadDir = System.getProperty("java.io.tmpdir") + "/uploads";
            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendEmail(Application application) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("ugtsic@sesap.com");
        helper.setTo(application.getEmail());
        helper.setSubject("Confirmação de envio do Currículo");

        // Email Content
        String emailContent = """
                <h3>Currículo Recebido!</h3>
                <p>Caro(a) %s,</p>
                <p>Obrigada por aplicar para o cargo de <strong>%s</strong>. Seus dados enviados serão confirmados abaixo:</p>
                <p><strong>Email:</strong> %s</p>
                <p><strong>Contato:</strong> %s</p>
                <p><strong>Escolaridade:</strong> %s</p>
                <p><strong>Observações:</strong> %s</p>
                <p>Nós recebemos a sua candidatura. O nosso time irá avaliar a sua aplicação e em breve retornaremos o contato.</p>
                <p>Atenciosamente,</p>
                <p>Equipe UGTSIC</p>
                """.formatted(application.getName(), application.getDesiredRole(), application.getEmail(),
                application.getPhone(), application.getEducation(), application.getObservations());

        helper.setText(emailContent, true);

        FileSystemResource file = new FileSystemResource(new File(application.getResumePath()));
        helper.addAttachment("Curriculo.pdf", file);

        mailSender.send(message);
    }
}