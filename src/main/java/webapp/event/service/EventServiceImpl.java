package webapp.event.service;


import core.service.BasicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.event.dto.EventDTO;
import webapp.event.pojo.Event;
import webapp.event.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl extends BasicService<EventRepository, Event, EventDTO>
        implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;


    @Autowired
    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepository) {
        super(modelMapper, eventRepository);
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean insert(EventDTO eventDTO) {
        Event event = modelMapper.map(eventDTO, Event.class);
        eventRepository.save(event);
        return true;
    }

    @Override
    public List<EventDTO> getAllEvent() {
        return getAllDTO();
    }

    @Override
    public Boolean updateWinner(EventDTO eventDTO) {
        eventRepository.updateEventWinners(eventDTO.getEventWinner1(), eventDTO.getEventWinner2(), eventDTO.getEventWinner3(), eventDTO.getEventStatus(), eventDTO.getEventNo());
        return true;
    }

    @Override
    public Boolean updateSignupNum(EventDTO eventDTO) {
        Integer signupNum = eventRepository.getEventSignupNum(eventDTO.getEventNo());
        Integer eventLimit = eventRepository.getEventLimit(eventDTO.getEventNo());
        if (signupNum == eventLimit) {
            return false;
        } else {
            eventRepository.updateEventSignupNum(1, eventDTO.getEventNo());
            return true;
        }
    }


    @Override
    public Boolean cancelSignupNum(EventDTO eventDTO) {
        Integer signupNum = eventRepository.getEventSignupNum(eventDTO.getEventNo());
        if (signupNum == 0) {
            return false;
        } else {
            eventRepository.updateEventSignupNum(-1, eventDTO.getEventNo());
            return true;
        }
    }

    @Override
    public Integer selectEventLimit(EventDTO eventDTO) {
        return eventRepository.getEventLimit(eventDTO.getEventNo());
    }

    @Override
    public Boolean updateEventStatus(EventDTO eventDTO){
        if(eventDTO.getEventStatus() == 0 || eventDTO.getEventStatus() == 1 || eventDTO.getEventStatus() == 2){
            eventRepository.setEventStatus(eventDTO.getEventStatus(),eventDTO.getEventLimit(), eventDTO.getSignupNum() ,eventDTO.getEventNo());
            return true;
        }else {
            return false;
        }


    }

}





