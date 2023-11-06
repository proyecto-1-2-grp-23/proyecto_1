from .base_command import BaseCommannd
from ..models.entrevista import Entrevista, EntrevistaJsonSchema
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..models.empresa import Empresa, EmpresaJsonSchema
from ..models.user import User, UserJsonSchema
from ..session import Session

class GetEntrevista(BaseCommannd):
    def __init__(self, entrevistaId):
        self.entrevistaId = entrevistaId
    
    def execute(self):
        id: int = self.entrevistaId
        session: Session = Session()
        obj: Entrevista = session.get(Entrevista, {'id': id})
        result: dict = EntrevistaJsonSchema().dump(obj)
        if(obj):
            funcionario: User = session.get(User, {'id':obj.idFuncionario})
            funcionario_json: dict = UserJsonSchema().dump(funcionario)
            empresa: Empresa = session.get(Empresa, {'id':obj.idEmpresa})
            empresa_json: dict = EmpresaJsonSchema().dump(empresa)
            candidato: Candidato = session.get(Candidato, {'id':obj.idCandidato})
            candidato_json: dict = CandidatoJsonSchema().dump(candidato)
            result = {**result, 'funcionario': funcionario_json, 'empresa':empresa_json, 'candidato':candidato_json}
        session.close()

        return result
        

