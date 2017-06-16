import { Component, OnInit } from '@angular/core';
import { InicioService } from './inicio.service';
import { Estado } from '../classes/estado.class';
import { UsuarioService } from '../usuario/usuario.service';
import { Usuario } from '../classes/usuario.class';
import { Router } from '@angular/router';
import { MascotaService } from '../mascota/mascota.service';
import { Mascota } from '../classes/mascota.class';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  logueado: Usuario;
  errorMessage: string;

  constructor(
    private inicioService: InicioService,
    private usuarioService: UsuarioService,
    private router: Router,
    private mascotasService: MascotaService
  ) { }

  estados: Estado[];
  mascota: string;
  text: string;
  mascotas: Mascota[];
  contador: number;
  color: string;
  image: string;

  ngOnInit() {
    this.getEstados();
    this.text = undefined;
    this.usuarioService.getPrincipal()
      .then(usuario => this.logueado = usuario)
      .catch(error => this.errorMessage = <any>error);
    this.mascotasService.buscarMascotas()
        .then(mascotas => {
          this.mascotas = mascotas;
          this.mascota = mascotas[0].nombre;
        })
        .catch(error => this.errorMessage = <any>error );
    this.contador = 0;
    this.color = "#449b44";
  }

  getImage(){
    this.image = this.inicioService.getImagen();
  }

  cargar(event){
      var files = event.target.files;
      var file = files[0];
    
    if (files && file) {
        var reader = new FileReader();

        reader.onload =this._handleReaderLoaded.bind(this);

        reader.readAsBinaryString(file);
    }
  }
  
  _handleReaderLoaded(readerEvt) {
     var binaryString = readerEvt.target.result;
     this.image= btoa(binaryString);
  }

  post() {
    if (this.text != undefined) {
      this.text = this.text.trim();
    }
    try {
      if (this.text == ""
          || this.text == undefined
          || this.text == null) {
      } else {
        this.text = this.text.trim();
        let estado: Estado = {
          fecha: Date.now(),
          mascota: this.mascota,
          texto: this.text,
          usuario: this.logueado.nombre,
          imagen: this.image?this.image:""
        };
        this.newEstado(estado);
        setTimeout(() => this.getEstados(), 1000);
        this.text = undefined;
        this.contador = 0;
      }
    }
    catch(e){
      return false;
    };
  }

  agregarMascota() {
    this.router.navigate(['/mascotas']);
  }

  getEstados() {
    this.inicioService.getEstados()
      .then(estados => this.estados = estados)
      .catch(error => this.errorMessage = error);
  }

  newEstado(estado: Estado) {
    this.inicioService.newEstado(estado);
  }

  contar() {
    if (this.text != undefined) {
      this.contador = this.text.length / 255 * 100;
    }  
  }
}