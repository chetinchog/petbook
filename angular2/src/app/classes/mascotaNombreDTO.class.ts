import { TipoAnimal } from '../classes/tipoAnimal.class';
import { Sexo } from '../classes/sexo.class';

export interface MascotaNombreDTO {
  id: number;
  nombre: string;
  fechaNacimiento: string;
  descripcion: string;
  tipoAnimal: string;
  sexo: string;
  textoPerdido: string;
}