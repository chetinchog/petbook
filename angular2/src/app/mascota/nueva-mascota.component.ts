import { Component, OnInit } from '@angular/core';
import { MascotaService} from './mascota.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { DatePickerPipe } from '../tools/common-pipes.pipe';
import { DatePickerModule } from 'ng2-datepicker';
import { Mascota } from '../classes/mascota.class';
import { Sexo } from '../classes/sexo.class';
import { SexoService } from '../sexo/sexo.service';
import { TipoAnimal } from '../classes/tipoAnimal.class';
import { TipoAnimalService } from '../tipoAnimal/tipoAnimal.service';
import * as Moment from 'moment';


@Component({
  selector: 'app-nueva-mascota',
  templateUrl: './nueva-mascota.component.html'
})
export class NuevaMascotaComponent implements OnInit {
  mascota: Mascota;
  errorMessage: string;
  formSubmitted: boolean;
  errors: string[] = [];
  sexoActivo: string;
  sexos: Sexo[];
  tipoAnimalActivo: string;
  tiposAnimal: TipoAnimal[];

  idEditado: number;
  nombreEditado: string;
  fechaNacimientoEditado: string;
  descripcionEditado: string;
  tipoAnimalEditado: number;
  sexoEditado: number;
  textoPerdidoEditado: string;

  nombre: string;
  fechaNacimiento: string;
  descripcion: string;
  tipoAnimal: number;
  sexo: number;
  textoPerdido: string;
  
 

  constructor(
    private mascotasService: MascotaService,
    private sexoService: SexoService,
    private tipoAnimalService: TipoAnimalService,
    private route: ActivatedRoute, private router: Router) {};
    

  ngOnInit() {
    this.mascota = {
      id: null,
      nombre: '',
      descripcion: null,
      fechaNacimiento: null,
      sexo: null,
      textoPerdido: null,
      tipoAnimal: null
    }
    this.route.params.subscribe(params => {
      let id = +params['id'];
      if (id) {
        this.mascotasService.buscarMascota(id)
          .then(mascota => {
            this.mascota = mascota;
            this.idEditado = mascota.id;
            this.nombreEditado = mascota.nombre;
            this.descripcionEditado = mascota.descripcion;
            this.fechaNacimientoEditado = mascota.fechaNacimiento;
            this.sexoEditado = mascota.sexo;
            this.tipoAnimalEditado = mascota.tipoAnimal;
            this.textoPerdidoEditado = mascota.textoPerdido;
      })  
          .catch(error => this.errorMessage = <any>error);
      }
    });

    this.sexoService.getSexos()
      .then(sexos =>{
        this.sexos = sexos;
        this.sexoActivo = sexos[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

      this.tipoAnimalService.getTiposAnimal()
      .then(tiposAnimal =>{
        this.tiposAnimal = tiposAnimal;
        this.tipoAnimalActivo = tiposAnimal[0].nombre;
      })
      .catch(error => this.errorMessage = <any>error);

  }

  post(){
    let mascota: Mascota = {
      id: this.idEditado,
      nombre: this.nombreEditado,
      fechaNacimiento: this.fechaNacimientoEditado,
      descripcion: this.descripcionEditado,
      tipoAnimal: this.tipoAnimalEditado,
      sexo: this.sexoEditado,
      textoPerdido: this.textoPerdidoEditado
    };

    this.guardarMascota(mascota);
  }

  submitForm() {
    this.cleanRestValidations();
    this.mascotasService.guardarMascota(this.mascota)
      .then(mascota => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));
  }
  
  
  onDelete() {
    this.cleanRestValidations();
    this.mascotasService.eliminarMascota(this.mascota.id)
      .then(any => this.router.navigate(['/mascotas']))
      .catch(error => this.procesarValidacionesRest(error));
  }

  cleanRestValidations() {
    this.errorMessage = undefined;
    this.errors = [];
  }

  procesarValidacionesRest(data: any) {
    if (data.message) {
      for (const error of data.message) {
        this.errors[error.path] = error.message;
      }
    } else {
      this.errorMessage = data.message;
    }
  }

  guardarMascota(mascota: Mascota) {
    this.router.navigate(['/inicio']);
    return this.mascotasService.guardarMascota(mascota);
  }

  newMascota(mascota: Mascota){
    return this.mascotasService.newMascota(mascota);
  }

  buscarSexo(id: number) {
    return this.sexoService.buscarSexo(id);
  }

   
}
