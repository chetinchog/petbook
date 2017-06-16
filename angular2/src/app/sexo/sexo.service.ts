import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { Sexo } from '../classes/sexo.class';

@Injectable()
export class SexoService extends RestBaseService {
  private sexosUrl = '/rest/sexos';

  constructor(private http: Http) { super(); }

  getSexos(): Promise<Sexo[]> {
    return this.http.get(SexoService.serverUrl + this.sexosUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as Sexo[];
      })
      .catch(this.handleError);
  }
}