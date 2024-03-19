package com.Razorpay.Controller;

import com.Razorpay.Payload.OrderDto;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping
    // http://localhost:8080/api/order
    public String CreateOrder(@RequestBody OrderDto dto) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_yowczX0x7NPhvn", "MfaH8OSJiQ4PCTokSPZP43qz");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",dto.getAmount());
        orderRequest.put("currency",dto.getCurrency());
        orderRequest.put("receipt", UUID.randomUUID().toString());
        JSONObject notes = new JSONObject();
        notes.put(dto.getNoteSubject(),dto.getNoteContent());
        orderRequest.put("notes",notes);

        Order order = razorpay.orders.create(orderRequest);
return order.get("id").toString();

    }

}
