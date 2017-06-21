import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import { Estado } from '../classes/estado.class';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class InicioService extends RestBaseService {
  private url = '/rest/inicio';
  
  constructor(private http: Http) { super(); }

  getEstados(): Promise<Estado[]> {
    return this.http
      .get(
        InicioService.serverUrl + this.url, 
        this.getRestHeader()
      )
      .toPromise()
      .then(
        response => {
          return response.json() as Estado[];
        }
      )
      .catch(this.handleError);
  }

  getLastEstados(fecha): Promise<Estado[]> {
    return this.http
      .get(
        InicioService.serverUrl + this.url + '/last/' + fecha, 
        this.getRestHeader()
      )
      .toPromise()
      .then(
        response => {
          return response.json() as Estado[];
        }
      )
      .catch(this.handleError);
  }

  newEstado(estado: Estado) {
    return this.http
      .post(
        InicioService.serverUrl + this.url,
        JSON.stringify(estado),
        this.getRestHeader()
      )
      .toPromise()
      .then()
      .catch();
  }

  deleteEstado(id) {
    this.http
      .delete(
        InicioService.serverUrl + this.url + '/' + id,
        this.getRestHeader()
      )
      .toPromise()
      .then()
      .catch();
  }
}