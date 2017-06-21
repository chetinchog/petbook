import { Injectable } from '@angular/core';
import { Http, Headers, Response, URLSearchParams } from '@angular/http';
import { RestBaseService } from '../tools/rest.tools';
import 'rxjs/add/operator/toPromise';
import { TipoAnimal } from '../classes/tipoAnimal.class';

@Injectable()
export class TipoAnimalService extends RestBaseService {
  private tiposAnimalUrl = '/rest/tipoanimal';

  constructor(private http: Http) { super(); }

  getTiposAnimal(): Promise<TipoAnimal[]> {
    return this.http.get(TipoAnimalService.serverUrl + this.tiposAnimalUrl, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as TipoAnimal[];
      })
      .catch(this.handleError);
  }

  getTipoAnimal(id: number): Promise<TipoAnimal> {
    return this.http.get(TipoAnimalService.serverUrl + this.tiposAnimalUrl + '/'+id, this.getRestHeader())
      .toPromise()
      .then(response => {
        return response.json() as TipoAnimal;
      })
      .catch(this.handleError);
  }

}