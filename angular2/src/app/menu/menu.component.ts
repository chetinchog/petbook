import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../usuario/usuario.service';
import { Usuario } from '../classes/usuario.class';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  logueado: Usuario;
  errorMessage: string;
  logedin = false;
  loginForm: FormGroup;

  constructor(
      fb: FormBuilder, 
      private usuarioService: UsuarioService,
      private router: Router
    ) {
    this.loginForm = fb.group({
      'username': [null, Validators.required],
      'password': [null, Validators.required]
    });
  }

  ngOnInit() {
    this.logueado = null;
    this.usuarioService.getPrincipal()
      .then(usuario => this.logueado = usuario)
      .catch(error => this.errorMessage = <any>error);
  }

  usuarioLogueado(): Usuario {
    return this.logueado;
  }

  login() {
    this.usuarioService.login(this.loginForm.value.username, this.loginForm.value.password)
      .then(usuario => {
        this.logueado = usuario;
        this.logedin = true;
        this.router.navigate(['/inicio']);
      })
      .catch(error => this.errorMessage = <any>error);
  }

  logout() {
    this.logueado = null;
    this.logedin = false;
    this.router.navigate(['/']);
    this.usuarioService.logout().then(null)
      .catch(error => this.errorMessage = <any>error);
  }
}
