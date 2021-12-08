package io.github.arthurgmr.service.implementation;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.entity.ClientOrder;
import io.github.arthurgmr.domain.entity.ItemOrder;
import io.github.arthurgmr.domain.entity.Product;
import io.github.arthurgmr.domain.enums.StatusOrder;
import io.github.arthurgmr.domain.repository.IClientOrderRepository;
import io.github.arthurgmr.domain.repository.IClientRepository;
import io.github.arthurgmr.domain.repository.IItemOrderRepository;
import io.github.arthurgmr.domain.repository.IProductRepository;
import io.github.arthurgmr.exception.NegotiateRule;
import io.github.arthurgmr.exception.OrderNotFound;
import io.github.arthurgmr.rest.dto.*;
import io.github.arthurgmr.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final IClientOrderRepository orderRepository;
    private final IClientRepository clientRepository;
    private final IProductRepository productRepository;
    private final IItemOrderRepository itemOrderRepository;

// NOT OBLIGATORY TO USE WHEN WAS SET THE @REQUIREDARGSCONSTRUCTOR
// REMEMBER TO USE "FINAL" IN THE DEPENDENCIES;
//    public OrderServiceImpl(
//            IClientOrderRepository orderRepository,
//            IClientRepository clientRepository,
//            IProductRepository productRepository
//    ) {
//        this.orderRepository = orderRepository;
//        this.clientRepository = clientRepository;
//        this.productRepository = productRepository;
//    }

    @Override
    public ClientOrder save(OrderDTO dataOrder) {
        Integer clientId = dataOrder.getClient_id();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new NegotiateRule("Client not found!"));

        ClientOrder order = new ClientOrder();
        order.setTotal(dataOrder.getTotal());
        order.setCreated_at(LocalDate.now());
        order.setClient(client);
        order.setStatus(StatusOrder.REALIZED);


        List<ItemOrder> itemsOrder = checkAndSetItem(order, dataOrder.getItems());
        orderRepository.save(order);
        itemOrderRepository.saveAll(itemsOrder);
        order.setItems(itemsOrder);

        return order;
    }

    private List<ItemOrder> checkAndSetItem(ClientOrder order, List<ItemOrderDTO> items) {
        // check list exists
        if(items.isEmpty()) {
            throw new NegotiateRule("Order without items!");
        }

        return items
                .stream()
                .map( item -> {
                    Integer productId = item.getProduct_id();
                    Product product = productRepository
                            .findById(productId)
                            .orElseThrow(() -> new NegotiateRule("Product not found! product_id: "+ productId));

                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setQuantity(item.getQuantity());
                    itemOrder.setClient_order(order);
                    itemOrder.setProduct_id(product);
                    itemOrder.setCreated_at(LocalDate.now());
                    return itemOrder;
                }).collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDTO getOrder(Integer id) {
        return orderRepository
                .findByIdFetchItems(id)
                .map(order -> convertOrderDTO(order))
                .orElseThrow(() -> new NegotiateRule("Order not found!"));
    }



    private ResponseOrderDTO convertOrderDTO(ClientOrder order) {
        return ResponseOrderDTO.builder()
                .code(order.getId())
                .nameClient(order.getClient().getName())
                .cpf(order.getClient().getCpf())
                .status(order.getStatus().name())
                .date(order.getCreated_at().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .total(order.getTotal())
                .items(convertItemsDTO(order.getItems()))
                .build();

    }

    private List<ResponseItemOrderDTO> convertItemsDTO(List<ItemOrder> items) {
        //check if list is empty;
        //case yes, will be return one empty list;
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items
                .stream()
                .map( item -> ResponseItemOrderDTO.builder()
                        .productDescription(item.getProduct_id().getDescription())
                        .unit_price(item.getProduct_id().getUnit_price())
                        .quantity(item.getQuantity())
                        .build()
        ).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void changeStatusOrder(Integer id, StatusOrder status) {
        orderRepository
                .findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFound());
    }

}
