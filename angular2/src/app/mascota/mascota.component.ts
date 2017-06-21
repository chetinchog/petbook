import { Component, OnInit } from '@angular/core';
import { MascotaService } from './mascota.service'
import { Observable } from 'rxjs/Rx';
import { Mascota } from '../classes/mascota.class';
import { MascotaNombreDTO } from '../classes/mascotaNombreDTO.class';
import { Sexo } from '../classes/sexo.class';
import { SexoService } from '../sexo/sexo.service';
import { TipoAnimal } from '../classes/tipoAnimal.class';
import { TipoAnimalService } from '../tipoAnimal/tipoAnimal.service';


@Component( {
    selector: 'app-mascota',
    templateUrl: './mascota.component.html'
})
export class MascotaComponent implements OnInit {
    errorMessage: string;
    mascotas: MascotaNombreDTO[];

    constructor( private mascotasService: MascotaService,
                private sexoService: SexoService,
                    private tipoAnimalService: TipoAnimalService) { }

    ngOnInit() {
        this.getNombreMascota();
    }

    funcionSexo(id: number) {
        return this.sexoService.buscarSexo(id)
                      .then(sex => {return sex.nombre;})
    }

    funcionTipoA(id: number) {
        return this.tipoAnimalService.getTipoAnimal(id)
                      .then(tipoA => {return tipoA.nombre;});
    }

    getNombreMascota() {
        this.mascotasService.buscarMascotaNombre()
        .then(mascotasNombre =>
            this.mascotas = mascotasNombre);
    }


}
