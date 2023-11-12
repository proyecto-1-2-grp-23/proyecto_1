export class Equipo {
  id: number;
  idEmpresa: number;
  descripcion: string;
  nombre: string;
  idFuncionario: number;

  constructor(
    id: number,
    idEmpresa: number,
    nombre: string,
    descripcion: string,
    idFuncionario: number
  ) {
    this.id = id;
    this.idEmpresa = idEmpresa;
    this.descripcion = descripcion;
    this.nombre = nombre;
    this.idFuncionario = idFuncionario;
  }
}

export type equipoCrear = Pick<
  Equipo,
  'idEmpresa' | 'nombre' | 'descripcion' | 'idFuncionario'
>;
