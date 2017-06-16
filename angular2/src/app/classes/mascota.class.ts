//import { TipoAnimal } from '../classes/tipoAnimal.class';

export interface Mascota {
  id: number;
  nombre: string;
  fechaNacimiento: string;
  descripcion: string;

  /* Agregar a EntityBean
  tipoAnimal: TipoAnimal;
  peso: number;
  indicadorPerdido: boolean;
  textoPerdido: string;
  */
}