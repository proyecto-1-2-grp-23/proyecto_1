from .base_command import BaseCommannd
from ..models.respuesta_enviada import RespuestaEnviada, RespuestaEnviadaSchema
from ..models.envio import Envio, EnvioSchema
from ..models.pregunta import Pregunta, PreguntaSchema
from ..models.respuesta import Respuesta, RespuestaSchema
from ..session import Session


class ObtenerResultados(BaseCommannd):
    def __init__(self, idProyecto, idCandidato):
        self.idProyecto = idProyecto
        self.idCandidato = idCandidato

    def execute(self):
        session = Session()
        envio = (
            session.query(Envio)
            .filter(
                Envio.idCandidato == self.idCandidato
                and Envio.idProyecto == self.idProyecto
            )
            .first()
        )

        import pdb

        pdb.set_trace()
        if not envio:
            return None

        respuestas_enviadas = session.query(RespuestaEnviada).filter(
            RespuestaEnviada.idEnvio == envio.id
        )
        respuestas_enviadas = RespuestaEnviadaSchema(many=True).dump(
            respuestas_enviadas
        )
        for respuesta_enviada in respuestas_enviadas:
            respuesta = session.get(
                Respuesta, {"id": respuesta_enviada.get("idRespuesta")}
            )
            pregunta = session.get(Pregunta, {"id": respuesta.idPregunta})
            # respuesta = RespuestaSchema().dump(respuesta)
            # pregunta = PreguntaSchema().dump(pregunta)
            respuesta_enviada["respuesta"] = RespuestaSchema().dump(respuesta)
            respuesta_enviada["pregunta"] = PreguntaSchema().dump(pregunta)
        envio = EnvioSchema().dump(envio)
        envio["respuestas_enviadas"] = respuestas_enviadas
        session.close()
        return envio
