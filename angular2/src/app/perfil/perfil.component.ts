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
  estadoCivilActivo: string;

  nombre = "Martín";
  apellido = "Monzo";
  email = "martinmgancia1@gmail.com";
  direccion = "Portugal 1439, GC";
  telefono = "4527059";

  constructor(fb: FormBuilder,
    private provinciaService: ProvinciaService,
    private sexoService: SexoService,
    private perfilService: PerfilService,
    private estadoCivilService: EstadoCivilService,
    private route: ActivatedRoute,
    private router: Router) {
    this.form = fb.group({
      'id': [null, null],
      'provincia': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'email': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'nombre': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'apellido': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'direccion': [null, Validators.required],
      'telefono': [null, Validators.required],
      'sexo': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      'estadoCivil': [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],

    })
    this.form.patchValue({ id: null, nombre: '', apellido: '', sexo: '', email: '', provincia: '', direccion: '', telefono: '', estadoCivil: '' });
  }

  ngOnInit() {
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

    this.perfilService.buscarPerfil()
      .then(perfil => this.form.patchValue(perfil))
      .catch(error => this.errorMessage = <any>error);
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

  guardarPerfil(){
    this.editar = false;
  }
}

