export class Candidato {
  id: number;
  nombreCompleto: string;
  edad: number;
  telefono: number;
  correo: string;
  pais: string;
  ciudad: string;
  idiomas: string;
  rasgosPersonalidad: string;
  password: string;

  constructor(
    id: number,
    nombreCompleto: string,
    edad: number,
    telefono: number,
    correo: string,
    pais: string,
    ciudad: string,
    idiomas: string,
    rasgosPersonalidad: string,
    password: string
  ) {
    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.edad = edad;
    this.telefono = telefono;
    this.correo = correo;
    this.pais = pais;
    this.ciudad = ciudad;
    this.idiomas = idiomas;
    this.rasgosPersonalidad = rasgosPersonalidad;
    this.password = password;
  }
}

export type candidatoRegistroPersonal = Pick<
  Candidato,
  | 'nombreCompleto'
  | 'edad'
  | 'telefono'
  | 'correo'
  | 'pais'
  | 'ciudad'
  | 'idiomas'
  | 'rasgosPersonalidad'
  | 'password'
>;
