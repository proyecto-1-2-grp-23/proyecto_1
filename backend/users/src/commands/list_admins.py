from .base_command import BaseCommannd
from ..models.entrevista import Entrevista, EntrevistaJsonSchema
from ..models.user import User, UserJsonSchema
from ..models.empresa import Empresa, EmpresaJsonSchema
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..session import Session


class ListAdmin(BaseCommannd):

    def execute(self):
        session = Session()
        admin_users = session.query(User).filter(User.correo.iendswith('@abc.com')).all()
        users = UserJsonSchema(many=True).dump(admin_users)
        session.close()
        return users