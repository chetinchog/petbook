import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { EstadoCivil } from '../classes/estadoCivil.class';

@Injectable()
export class EstadoCivilService extends RestBaseService {
  private estadoCivilUrl = '/rest/estadocivil';

  constructor(private http: Http) { super(); }

  getEstadosCiviles(): Promise<EstadoCivil[]> {
    return this.http.get(EstadoCivilService.serverUrl + this.estadoCivilUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as EstadoCivil[];
      })
      .catch(this.handleError);
  }
}