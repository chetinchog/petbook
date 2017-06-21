import { TipoAnimal } from '../classes/tipoAnimal.class';
import { Sexo } from '../classes/sexo.class';

export interface Mascota {
  id: number;
  nombre: string;
  fechaNacimiento: string;
  descripcion: string;
  tipoAnimal: number;
  sexo: number;
  textoPerdido: string;
}