//package com.test.tmhkc.Listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//
//@Slf4j
//@Component
//public class TopicListener {
//
//
//    @JmsListener(destination = "upload.vehicle.message" , containerFactory = "jmsListenerContainerTopic")
//    public void receive(Message message) throws JMSException {
//
//        if (message instanceof MapMessage){
//            MapMessage mapMessage = (MapMessage) message;
//            log.info("vehicle_id--->"+mapMessage.getString("vehicle_id"));
//            log.info("crossing_id--->"+mapMessage.getString("crossing_id"));
//            log.info("lane_id--->"+mapMessage.getString("lane_id"));
//            log.info("image_server_id--->"+mapMessage.getString("image_server_id"));
//            log.info("plate_info--->"+mapMessage.getString("plate_info"));
//            log.info("plate_color--->"+mapMessage.getString("plate_color"));
//            log.info("alarm_time--->"+mapMessage.getString("alarm_time"));
//            log.info("vehicle_speed--->"+mapMessage.getString("vehicle_speed"));
//            log.info("vehicle_type--->"+mapMessage.getString("vehicle_type"));
//            log.info("veh_color_depth--->"+mapMessage.getString("veh_color_depth"));
//            log.info("vehicle_color--->"+mapMessage.getString("vehicle_color"));
//            log.info("pic_plate--->"+mapMessage.getString("pic_plate"));
//            log.info("pic_vehicle--->"+mapMessage.getString("pic_vehicle"));
//            log.info("alarm_action--->"+mapMessage.getString("alarm_action"));
//            log.info("plate_type--->"+mapMessage.getString("plate_type"));
//            log.info("crossing_name--->"+mapMessage.getString("crossing_name"));
//            log.info("remark--->"+mapMessage.getString("remark"));
//            log.info("crossing_index_code--->"+mapMessage.getString("crossing_index_code"));
//            log.error("---------------------");
//        }
//    }
//}
