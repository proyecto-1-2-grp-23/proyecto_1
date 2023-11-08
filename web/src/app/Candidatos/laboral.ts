import { Candidato } from "./candidato";

export class Laboral {
  id: number;
  nombre_empresa : string;
  rol : string;
  funciones : number;
  fecha_inicio : string;
  fecha_fin : string;
  habilidades : string
  idUsuario: string

  constructor(
    id: number,
    nombre_empresa: string,
    rol: string,
    funciones: number,
    fecha_inicio: string,
    fecha_fin: string,
    habilidades: string,
    idUsuario: string
  ) {
    this.id = id;
    this.nombre_empresa = nombre_empresa;
    this.rol = rol;
    this.funciones = funciones;
    this.fecha_inicio = fecha_inicio;
    this.fecha_fin = fecha_fin;
    this.habilidades = habilidades
    this.idUsuario = idUsuario
  }
}

export type candidatoRegistroLaboral = Pick<
Laboral,
  | 'nombre_empresa'
  | 'rol'
  | 'funciones'
  | 'fecha_inicio'
  | 'fecha_fin'
  | 'habilidades'
>;
