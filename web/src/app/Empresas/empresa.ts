export class Empresa {
  id: number;
  razonSocial: string;
  tipoEmpresa: string;
  correo: string;
  pais: string;
  ciudad: string;
  verticalesNegocio: string;
  password: string;

  constructor(
    id: number,
    razonSocial: string,
    tipoEmpresa: string,
    correo: string,
    pais: string,
    ciudad: string,
    verticalesNegocio: string,
    password: string
  ) {
    this.id = id;
    this.razonSocial = razonSocial;
    this.tipoEmpresa = tipoEmpresa;
    this.correo = correo;
    this.pais = pais;
    this.ciudad = ciudad;
    this.verticalesNegocio = verticalesNegocio;
    this.password = password;
  }
}

export type empresaoRegistro = Pick<
  Empresa,
  | 'razonSocial'
  | 'tipoEmpresa'
  | 'correo'
  | 'pais'
  | 'ciudad'
  | 'verticalesNegocio'
  | 'password'
>;

export type emrpesaSignIn = Pick<Empresa, 'correo' | 'password'>;
