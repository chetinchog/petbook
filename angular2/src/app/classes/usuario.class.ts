export interface Usuario {
  login: string;
  nombre: string;
  email: string;
  finVigencia: string;
  inicioVigencia: string;
  activo: boolean;
  roles: string[];
}