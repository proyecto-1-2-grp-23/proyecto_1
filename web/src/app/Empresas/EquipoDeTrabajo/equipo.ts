export class Equipo {
  id: number;
  descripcion: string;
  nombre: string;

  constructor(id: number, nombre: string, descripcion: string) {
    this.id = id;
    this.descripcion = descripcion;
    this.nombre = nombre;
  }
}

export type equipoCrear = Pick<Equipo, 'nombre' | 'descripcion'>;
