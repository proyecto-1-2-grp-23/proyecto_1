export class Equipo {
  id: number;
  idEmpresa: number;
  descripcion: string;
  nombre: string;

  constructor(
    id: number,
    idEmpresa: number,
    nombre: string,
    descripcion: string
  ) {
    this.id = id;
    this.idEmpresa = idEmpresa;
    this.descripcion = descripcion;
    this.nombre = nombre;
  }
}

export type equipoCrear = Pick<Equipo, 'idEmpresa' | 'nombre' | 'descripcion'>;
