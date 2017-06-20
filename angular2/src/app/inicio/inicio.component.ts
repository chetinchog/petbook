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
  nombre: string;
  errorMessage: string;

  constructor(
    private inicioService: InicioService,
    private usuarioService: UsuarioService,
    private router: Router,
    private mascotasService: MascotaService
  ) { }

  estados: Estado[];
  idMascota: number;
  mascota: string;
  detalleMascota: Mascota;
  text: string;
  mascotas: Mascota[];
  contador: number;
  color: string;
  image: string;
  cargarImagen = "Cargar Imagen";
  fileName: string;

  ngOnInit() {
    this.getEstados();
    this.text = undefined;
    this.usuarioService.getPrincipal()
      .then(usuario => {
        this.logueado = usuario;
        this.nombre = usuario.nombre;
      })
      .catch(error => this.errorMessage = <any>error);
    this.mascotasService.buscarMascotas()
        .then(mascotas => {
          this.mascotas = mascotas;
        })
        .catch(error => this.errorMessage = <any>error );
    this.contador = 0;
    this.color = "#449b44";
    this.fileName = this.cargarImagen;
  }

  cargar(event){
      var files = event.target.files;
      var file = files[0];
    
    if (files && file) {
        var reader = new FileReader();

        reader.onload =this._handleReaderLoaded.bind(this);

        reader.readAsBinaryString(file);

        this.fileName = file.name;
    }
  }
  
  _handleReaderLoaded(readerEvt) {
     var binaryString = readerEvt.target.result;
     this.image = btoa(binaryString);
  }

  post() {
    if (this.text != undefined) {
      this.text = this.text.trim();
    }
    try {
      if ((this.text == "" && this.image == undefined)
          || (this.text == undefined && this.image == undefined)) {
      } else {
        this.mascotasService.buscarMascota(this.idMascota)
          .then(mascota => {
            this.mascota = mascota.nombre;
            this.generarEstado();
          });
        
        setTimeout(() => this.getEstados(), 1000);
      }
    }
    catch(e){
      return false;
    };
  }

  agregarMascota() {
    this.router.navigate(['/mascotas']);
  }

  generarEstado(){
    let estado: Estado = {
      id: 0,
      fecha: Date.now(),
      mascota: this.mascota,
      texto: this.text?this.text:"",
      usuario: this.logueado.nombre,
      idMascota: this.idMascota,
      imagen: this.image?this.image:""
    };
    this.newEstado(estado);
    this.text = undefined;
    this.contador = 0;
    this.image = "";
    this.fileName = this.cargarImagen;
  }

  getEstados() {
    this.inicioService.getEstados()
      .then(estados => {
        this.estados = estados;
      })
      .catch(error => this.errorMessage = error);
  }

  newEstado(estado: Estado) {
    this.inicioService.newEstado(estado);
  }

  delete(id) {
    this.inicioService.deleteEstado(id);
    setTimeout(() => this.getEstados(), 1000);
  }

  verMascota(id){
    this.mascotasService.buscarMascota(id)
          .then(mascota => this.detalleMascota = mascota)
          .catch(error => this.errorMessage = <any>error);
  }

  cerrarDetalle() {
    this.detalleMascota = undefined;
  }

  contar() {
    if (this.text != undefined) {
      this.contador = this.text.length / 255 * 100;
    }  
  }
}