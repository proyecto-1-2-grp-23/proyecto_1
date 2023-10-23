export class Candidato {
  id: number;
  correo: string;
  password: string;

  constructor(id: number, correo: string, password: string) {
    this.id = id;
    this.correo = correo;
    this.password = password;
  }
}

export type candidatoSignIn = Pick<Candidato, 'correo' | 'password'>;
