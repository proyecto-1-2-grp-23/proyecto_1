from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists


class CreateEmpresa(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        # try:
        user_data = {
            "correo": self.data.pop("correo"),
            "password": self.data.pop("password"),
            "ciudad": self.data.pop("ciudad"),
            "pais": self.data.pop("pais"),
        }
        posted_user = UserSchema(only=("correo", "password", "ciudad", "pais")).load(
            user_data
        )
        user = User(**posted_user)
        posted_empresa = EmpresaSchema(
            only=("razonSocial", "tipoEmpresa", "verticalesNegocio")
        ).load(self.data)
        empresa = Empresa(**posted_empresa, idUsuario=user.id)
        session = Session()

        if self.correo_exist(session, user_data["correo"]):
            session.close()
            raise UserAlreadyExists()

        session.add(user)
        session.add(empresa)
        session.commit()
        new_user = CreatedUserJsonSchema().dump(user)
        session.close()

        return new_user

    def correo_exist(self, session, correo):
        return len(session.query(User).filter_by(correo=correo).all()) > 0
