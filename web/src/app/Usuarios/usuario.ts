export class Candidato {
  id: number;
  correo: string;
  contraseña: string;

  constructor(id: number, correo: string, contraseña: string) {
    this.id = id;
    this.correo = correo;
    this.contraseña = contraseña;
  }
}

export type candidatoSignIn = Pick<Candidato, 'correo' | 'contraseña'>;
