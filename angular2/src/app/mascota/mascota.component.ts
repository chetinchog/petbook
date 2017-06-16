import { Component, OnInit } from '@angular/core';
import { MascotaService } from './mascota.service';
import { Observable } from 'rxjs/Rx';
import { Mascota } from '../classes/mascota.class';

@Component( {
    selector: 'app-mascota',
    templateUrl: './mascota.component.html'
})
export class MascotaComponent implements OnInit {
    errorMessage: string;
    mascotas: Mascota[];

    constructor( private mascotasService: MascotaService ) { }

    ngOnInit() {
        this.mascotasService.buscarMascotas()
          .then(mascotas => this.mascotas = mascotas)
          .catch(error => this.errorMessage = <any>error );
    }
}
