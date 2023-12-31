from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.candidato import CandidatoSchema, Candidato
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists


class CreateAdmin(BaseCommannd):
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

        session = Session()

        if self.correo_exist(session, user_data["correo"]):
            session.close()
            raise UserAlreadyExists()

        session.add(user)
        session.commit()
        new_user = CreatedUserJsonSchema().dump(user)
        session.close()

        return new_user

    # except TypeError:
    #     raise IncompleteParams()

    def correo_exist(self, session, correo):
        return len(session.query(User).filter_by(correo=correo).all()) > 0
