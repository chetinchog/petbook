import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProvinciaService } from '../provincia/provincia.service';
import { PerfilService } from './perfil.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { Perfil } from '../classes/perfil.class';
import { Provincia } from '../classes/provincia.class';
import { SexoService } from '../sexo/sexo.service';
import { Sexo } from '../classes/sexo.class';
import { EstadoCivil } from '../classes/estadoCivil.class';
import { EstadoCivilService } from '../estadoCivil/estadoCivil.service';
import { TipoAnimal } from '../classes/tipoAnimal.class';
import { TipoAnimalService } from '../tipoAnimal/tipoAnimal.service';


@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  form: FormGroup;
  errorMessage: string;
  formSubmitted: boolean;
  
  provincias: Provincia[];
  sexos: Sexo[];
  estadosCiviles: EstadoCivil[];
  editar = false;
  sexoActivo: string;
  provinciaActiva: string;
  provinciaActiva2: string;
  estadoCivilActivo: string;

  perfil: Perfil;
  perfilEditado: Perfil;

  id: number; 
  nombre: string;
  apellido: string;
  pcia: string;
  provincia: number;
  email: string
  direccion: string
  telefono: string;
  estadoCivil: number;
  fechaNacimiento: string;
  sexo: number;
  sex: string;
  estCiv: string;

  constructor(fb: FormBuilder,
    private provinciaService: ProvinciaService,
    private sexoService: SexoService,
    private perfilService: PerfilService,
    private estadoCivilService: EstadoCivilService,
    private route: ActivatedRoute,
    private router: Router) {
    this.form = fb.group({
      'provincia': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'email': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'nombre': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'apellido': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'direccion': [null, Validators.required],
      'telefono': [null, Validators.required],
      'sexo': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'estadoCivil': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],

    })
   
  }

  ngOnInit() {

    this.perfilService.buscarPerfil()
      .then(perfil => {
        this.perfil = perfil; 
        this.perfilEditado = perfil;
        if(this.perfil.provincia != null){
          this.buscarProvincia(perfil.provincia);
        }
        if(this.perfil.sexo != null){
          this.buscarSexo(perfil.sexo);       
        }
        if(this.perfil.estadoCivil != null) {
          this.buscarEstadoCivil(perfil.estadoCivil);
        }
       
        
    })
      .catch(error => this.errorMessage = <any>error);

    this.provinciaService.getProvincias()
      .then(provincias => {
        this.provincias = provincias;
        this.provinciaActiva = provincias[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

    this.sexoService.getSexos()
      .then(sexos =>{
        this.sexos = sexos;
        this.sexoActivo = sexos[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

    this.estadoCivilService.getEstadosCiviles()
      .then(estadosCiviles => {
        this.estadosCiviles = estadosCiviles;
        this.estadoCivilActivo = estadosCiviles[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);   
  }

  post() {
    let perfil: Perfil = {
      id: this.perfilEditado.id,
      nombre: this.perfilEditado.nombre,
      apellido: this.perfilEditado.apellido,
      provincia: this.perfilEditado.provincia,
      email: this.perfilEditado.email,
      direccion: this.perfilEditado.direccion,
      telefono: this.perfilEditado.telefono,
      estadoCivil: this.perfilEditado.estadoCivil,
      sexo: this.perfilEditado.sexo,
    }
    this.guardarPerfil(perfil);
    
  }

  submitForm() {
    this.cleanRestValidations();
    if (this.form.valid) {
      this.perfilService.guardarPerfil(this.form.value)
        .then(usuario => this.router.navigate(['/']))
        .catch(error => this.errorMessage = <any>error);
    } else {
      this.formSubmitted = true;
    }
  }


  cleanRestValidations() {
    //    this.form.controls['nombre'].setValidity( "rest", true );
    //    $scope.form.fechaNacimiento.$setValidity( "rest", true );
    //    $scope.form.descripcion.$setValidity( "rest", true );
  }

  procesarValidacionesRest(data) {
    /*   if ( data.message ) {
           for ( var i in data.message ) {
               var error = data.message[i];
               if ( $scope.form[error.path] ) {
                   $scope.form[error.path].$setValidity( "rest", false );
                   $scope.form[error.path].$error.restMessage = error.message;
               }
           }
       } else {
           toastr.error( "Error al grabar el perfil.", data.message );
       }*/
  }

  editarPerfil() {
    this.editar = true;
  }

  guardarPerfil(perfil){
    this.deseditar();
    this.cargar();
    this.router.navigate(['/inicio']);
    return this.perfilService.guardarPerfil(perfil);
  }

  newPerfil(perfil: Perfil){
    this.perfilService.newPerfil(perfil);
  }

  deseditar() {
    this.cargar();
    this.editar = false;
  }
  buscarProvincia(id: number) {
    this.provinciaService.buscarProvincia(id)
    .then(provincia => {this.pcia = provincia.nombre;});
  }

   buscarSexo(id: number) {
    this.sexoService.buscarSexo(id)
    .then(sexo => {this.sex = sexo.nombre;});
  }

   
  cargar() {
    this.perfilService.buscarPerfil()
      .then(perfil => {
        this.perfil = perfil; 
        this.perfilEditado = perfil;
        if(this.perfil.provincia != null){
          this.buscarProvincia(perfil.provincia);
        }
        if(this.perfil.sexo != null){
          this.buscarSexo(perfil.sexo);       
        }
        if(this.perfil.estadoCivil != null) {
          this.buscarEstadoCivil(perfil.estadoCivil);
        }
        
    })
      .catch(error => this.errorMessage = <any>error);

    this.provinciaService.getProvincias()
      .then(provincias => {
        this.provincias = provincias;
        this.provinciaActiva = provincias[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

    this.sexoService.getSexos()
      .then(sexos =>{
        this.sexos = sexos;
        this.sexoActivo = sexos[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

    this.estadoCivilService.getEstadosCiviles()
      .then(estadosCiviles => {
        this.estadosCiviles = estadosCiviles;
        this.estadoCivilActivo = estadosCiviles[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

    
  }

  buscarEstadoCivil(id: number) {
    this.estadoCivilService.buscarEstadoCivil(id)
    .then(estadoCivil => {this.estCiv = estadoCivil.nombre;});
  }
}

