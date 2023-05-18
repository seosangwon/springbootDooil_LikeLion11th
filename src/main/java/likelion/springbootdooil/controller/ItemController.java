package likelion.springbootdooil.controller;

import likelion.springbootdooil.domain.Item;
import likelion.springbootdooil.service.ItemService;
import likelion.springbootdooil.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemServiceImpl){
        this.itemService = itemServiceImpl;
    }

    @GetMapping("new")
    public String createForm(Model model){
        model.addAttribute("itemForm",new Item());
        return "items/createItemForm";
    }

    //생성 + 실행 후에 redirect로 items로 이동 하는데 Getmapping""이 실행되어서 findAll 메서드가 실행된다
    // 제출을 누르면 url이 items로 가고, RequestMapping으로 items가 자동이고, ""이 Getmapping이여서
    //타고타고 실행된다
    @PostMapping("new")
    public String create(Item item){
        itemService.save(item);
        return "redirect:/items";
    }

    //전체조회
    @GetMapping("")
    public String findAll(Model model) {

        List<Item> itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        return "items/itemList";
    }

    //update itemFormUpdate  View에게 던져줘아 햠
    // url mapping을 보니 /items/제품 번호(int) /update
    @GetMapping("{id}/update")
    public String updateForm(@PathVariable Long id, Model model){
        //수정 할 Item을 pk에 따라 service 로직을 이용하여 찾아와 Item 객채에 생성하여 넣어준다
        //그 item의 정보를  View에게 Controller가 던져준다
        Item item = itemService.findById(id);
        model.addAttribute("itemFormUpdate",item);

        return "items/updateItemForm";
    }

    //주의 submit 눌렀을 때 url이 /items/update로 되어있음 {id}정보 url에 없음: 당연한 것
    @PostMapping("update")
    public String update(@RequestParam Long id, @ModelAttribute Item item) {
        itemService.update(id, item);
        return "redirect:/items";
    }






}
