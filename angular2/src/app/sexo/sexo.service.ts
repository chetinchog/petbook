import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { Sexo } from '../classes/sexo.class';

@Injectable()
export class SexoService extends RestBaseService {
  private sexosUrl = '/rest/sexo';

  constructor(private http: Http) { super(); }

  getSexos(): Promise<Sexo[]> {
    return this.http.get(SexoService.serverUrl + this.sexosUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Sexo[];
      })
      .catch(this.handleError);
  }

  buscarSexo(id: number): Promise<Sexo> {
    return this.http.get(SexoService.serverUrl + this.sexosUrl+"/"+id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Sexo;
      })
      .catch(this.handleError);
  }
}