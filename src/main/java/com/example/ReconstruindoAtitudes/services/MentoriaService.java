//package com.example.ReconstruindoAtitudes.services;
//
//import com.example.ReconstruindoAtitudes.Model.MentorModel;
//import com.example.ReconstruindoAtitudes.Model.MentoriaModel;
//import com.example.ReconstruindoAtitudes.Repository.MentorRepository;
//import com.example.ReconstruindoAtitudes.Repository.MentoriaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class MentoriaService {
//
//    @Autowired
//    private MentoriaRepository mentoriaRepository;
//
//    @Autowired
//    private MentorRepository mentorRepository;
//
//    public String agendarMentoria(Long mentorId, LocalDateTime horario) {
//        // Verifica se o ID do mentor é nulo
//        if (mentorId == null) {
//            return "ID do mentor não pode ser nulo.";
//        }
//
//        Optional<MentorModel> optionalMentor = mentorRepository.findById(mentorId);
//
//        if (optionalMentor.isPresent()) {
//            MentorModel mentor = optionalMentor.get();
//
//            if (mentoriaRepository.existsByMentorAndHorario(mentor, horario)) {
//                // Horário já está ocupado, sugere outro horário
//                LocalDateTime novoHorario = horario.plusHours(1);
//                while (mentoriaRepository.existsByMentorAndHorario(mentor, novoHorario)) {
//                    novoHorario = novoHorario.plusHours(1);
//                }
//                return "Horário ocupado. Novo horário sugerido: " + novoHorario.toString();
//            } else {
//                MentoriaModel mentoria = new MentoriaModel();
//                mentoria.setMentor(mentor);
//                mentoria.setHorario(horario);
//                mentoriaRepository.save(mentoria);
//                return "Mentoria agendada com sucesso!";
//            }
//        } else {
//            return "Mentor não encontrado.";
//        }
//    }
//
//}
