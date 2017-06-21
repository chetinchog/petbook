import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import { Mascota } from '../classes/mascota.class';
import 'rxjs/add/operator/toPromise';
import { MascotaNombreDTO } from '../classes/mascotaNombreDTO.class';


@Injectable()
export class MascotaService extends RestBaseService {
  private url = '/rest/mascota';

  constructor(private http: Http) { super(); }

  buscarMascotas(): Promise<Mascota[]> {
    return this.http.get(MascotaService.serverUrl + this.url, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Mascota[];
      })
      .catch(this.handleError);
  }

  buscarMascota(id: number): Promise<Mascota> {
    return this.http.get(MascotaService.serverUrl + this.url + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Mascota;
      })
      .catch(this.handleError);
  }

  findMascota(id: number): Promise<Mascota> {
    return this.http.get(MascotaService.serverUrl + this.url + '/detalle/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Mascota;
      })
      .catch(this.handleError);
  }

  guardarMascota(value: Mascota): Promise<Mascota> {
    if (value.id && value.id > 0) {
      return this.http.post(MascotaService.serverUrl + this.url + '/' + value.id, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Mascota;
      })
      .catch(this.handleError);
    } else {
      return this.http.post(MascotaService.serverUrl + this.url, JSON.stringify(value), this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Mascota;
      })
      .catch(this.handleError);
    }
  }

  eliminarMascota(id: number): Promise<any> {
    if (id) {
      return this.http.delete(MascotaService.serverUrl + this.url + '/' + id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return "";
      })
      .catch(this.handleError);
    }
  }

  newMascota(mascota: Mascota){
    this.http
      .post(
        MascotaService.serverUrl + this.url,
        JSON.stringify(mascota),
        this.getRestHeader()
      )
      .toPromise()
      .then()
      .catch();
  }

    buscarMascotaNombre(): Promise<MascotaNombreDTO[]> {
    return this.http.get(MascotaService.serverUrl + this.url + '/nombre/', this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as MascotaNombreDTO[];
      })
      .catch(this.handleError);
  }


}
