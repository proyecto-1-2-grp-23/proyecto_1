from .base_command import BaseCommannd
from ..models.respuesta_enviada import RespuestaEnviada
from ..models.envio import Envio
from ..session import Session


class RegistrarResultados(BaseCommannd):
    def __init__(self, data, idEnvio):
        self.data = data
        self.idEnvio = idEnvio

    def execute(self):
        body: dict = self.data.copy()
        respuestas_enviadas = body.pop("respuestasEnviadas")
        session = Session()
        envio = session.get(Envio, {"id": self.idEnvio})

        if not envio:
            return None

        envio.observaciones = body.get("observaciones")

        for respuesta in respuestas_enviadas:
            respuesta_db = session.get(RespuestaEnviada, {"id": respuesta.get("id")})
            if respuesta_db:
                respuesta_db.correcta = respuesta.get("correcta")

        session.commit()
        session.close()
        return body
