from .base_command import BaseCommannd
from ..models.entrevista import Entrevista, EntrevistaJsonSchema
from ..models.user import User, UserJsonSchema
from ..models.empresa import Empresa, EmpresaJsonSchema
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..session import Session


class ListEntrevista(BaseCommannd):

    def execute(self):
        session = Session()
        entrevistas = session.query(Entrevista).all()
        entrevistas_json = []
        for entrevista in entrevistas:
            entrevista_json = EntrevistaJsonSchema().dump(entrevista)
            funcionario: User = session.get(User, {'id':entrevista.idFuncionario})
            funcionario_json: dict = UserJsonSchema().dump(funcionario)
            empresa: Empresa = session.get(Empresa, {'id':entrevista.idEmpresa})
            empresa_json: dict = EmpresaJsonSchema().dump(empresa)
            candidato: Candidato = session.get(Candidato, {'id':entrevista.idCandidato})
            candidato_json: dict = CandidatoJsonSchema().dump(candidato)
            entrevista_json = {**entrevista_json, 'funcionario': funcionario_json, 'empresa':empresa_json, 'candidato':candidato_json}
            entrevistas_json.append(entrevista_json)
        return entrevistas_json


    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict