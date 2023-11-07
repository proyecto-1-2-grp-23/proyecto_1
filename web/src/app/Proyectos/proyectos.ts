export class Candidato {
  id: number;
  nombre: string;
  descripcion: string;
  perfiles: string;
  conocimientos_tecnicos: string;
  startDate: Date;
  finishDate: Date;
  habilidades_blandas: string;

  constructor(
    id: number,
    nombre: string,
    descripcion: string,
    perfiles: string,
    conocimientos_tecnicos: string,
    startDate: Date,
    finishDate: Date,
    habilidades_blandas: string
  ) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.perfiles = perfiles;
    this.conocimientos_tecnicos = conocimientos_tecnicos;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.habilidades_blandas = habilidades_blandas;
  }
}

export type proyectoCrear = Pick<
  Candidato,
  | 'nombre'
  | 'descripcion'
  | 'perfiles'
  | 'conocimientos_tecnicos'
  | 'habilidades_blandas'
  | 'startDate'
  | 'finishDate'
>;
