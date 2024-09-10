package com.beautysalon.role;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @PostMapping("/createRole")
    public ResponseEntity<Integer> createRole(
            @Valid @RequestBody RoleRequest roleRequest
    ){
        return ResponseEntity.ok(service.saveRole(roleRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> allRoles(){
        return ResponseEntity.ok(service.findAllRoles());
    }

    @PatchMapping("/{userId}&{roleId}")
    public void assignRole(
            @PathVariable Long userId,
            @PathVariable Integer roleId
    ){
        service.assignRoles(userId, roleId);
    }

}
