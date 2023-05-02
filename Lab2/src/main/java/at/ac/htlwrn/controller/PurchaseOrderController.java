package at.ac.htlwrn.controller;

import at.ac.htlwrn.dto.ApiResponse;
import at.ac.htlwrn.dto.PurchaseOrderDto;
import at.ac.htlwrn.dto.UserDto;
import at.ac.htlwrn.model.PurchaseOrder;
import at.ac.htlwrn.model.User;
import at.ac.htlwrn.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")

public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ApiResponse<PurchaseOrder> saveOrder(@RequestBody PurchaseOrderDto order){
        return new ApiResponse<>(HttpStatus.OK.value(), "Order saved successfully.", purchaseOrderService.save(order));
    }

    @GetMapping
    public ApiResponse<List<PurchaseOrder>> listOrders() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Product list fetched successfully.", purchaseOrderService.find(false, false)); //all
    }

    @GetMapping("/{id}")
    public ApiResponse<PurchaseOrder> getById(@PathVariable Long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Order fetched successfully.", purchaseOrderService.findById(id));
    }

    @PutMapping("/cancel/{id}")
    public ApiResponse<Void> cancel(@PathVariable Long id) {
        purchaseOrderService.cancel(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
    }

    @PutMapping("/finish/{id}")
    public ApiResponse<Void> finish(@PathVariable Long id) {
        purchaseOrderService.finish(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
    }

}
