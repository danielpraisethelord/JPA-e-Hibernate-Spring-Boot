package com.app.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * Las anotaciones @Embeddable y @Embedded en JPA (Java Persistence API) se
 * utilizan para trabajar con tipos de datos compuestos que pueden ser
 * incorporados dentro de entidades. Estas anotaciones permiten encapsular
 * grupos de atributos en una clase independiente y reutilizable.
 * 
 * @Embeddable
 *             Función: Marca una clase como embebible. Esto significa que los
 *             atributos de esta clase pueden ser incorporados en una entidad.
 *             Uso: Se utiliza para definir clases que no tienen una identidad
 *             propia (sin @Id), pero cuyos atributos pueden formar parte de una
 *             entidad.
 * 
 *             Uso en conjunto
 *             Cuando se utilizan juntos, @Embeddable y @Embedded permiten una
 *             mayor modularidad y reutilización del código. Los atributos
 *             definidos en la clase embebible (@Embeddable) se incorporan en la
 *             tabla de la entidad que la contiene (@Embedded), sin necesidad de
 *             una relación de entidad a entidad.
 */

@Embeddable
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*
     * Las
     * anotaciones @PrePersist, @PreUpdate, @PreRemove, @PostPersist, @PostUpdate, @PostRemove,
     * y @PostLoad en JPA (Java Persistence API) se utilizan para definir métodos de
     * ciclo de vida en entidades. Estos métodos permiten ejecutar lógica
     * personalizada antes o después de ciertas operaciones del ciclo de vida de una
     * entidad. Aquí te explico cada una de ellas en detalle:
     * 
     * @PreRemove
     * Función: Se utiliza para ejecutar un método justo antes de que una entidad
     * sea eliminada de la base de datos.
     * Uso: Este método puede ser utilizado para realizar tareas de limpieza o
     * validaciones antes de que la entidad sea eliminada.
     * 
     * @PostPersist
     * Función: Se utiliza para ejecutar un método justo después de que una entidad
     * haya sido persistida en la base de datos.
     * Uso: Este método puede ser utilizado para realizar operaciones de seguimiento
     * o notificación una vez que la entidad ha sido insertada.
     * 
     * @PostUpdate
     * Función: Se utiliza para ejecutar un método justo después de que una entidad
     * haya sido actualizada en la base de datos.
     * Uso: Este método puede ser utilizado para realizar operaciones de seguimiento
     * o notificación una vez que la entidad ha sido actualizada.
     * 
     * @PostRemove
     * Función: Se utiliza para ejecutar un método justo después de que una entidad
     * haya sido eliminada de la base de datos.
     * Uso: Este método puede ser utilizado para realizar operaciones de seguimiento
     * o notificación una vez que la entidad ha sido eliminada.
     * 
     * @PostLoad
     * Función: Se utiliza para ejecutar un método justo después de que una entidad
     * ha sido cargada desde la base de datos.
     * Uso: Este método puede ser utilizado para inicializar valores que no están
     * persistidos en la base de datos o para realizar alguna lógica adicional una
     * vez que la entidad ha sido cargada.
     */

    /*
     * @PrePersist
     * Función: Se utiliza para ejecutar un método justo antes de que una entidad
     * sea persistida en la base de datos.
     * Uso: Este método puede ser utilizado para inicializar valores predeterminados
     * o para realizar validaciones antes de que la entidad se inserte en la base de
     * datos.
     */
    @PrePersist
    public void prePersist() {
        System.out.println("Evento del ciclo de vida del entity pre-persist");
        this.createAt = LocalDateTime.now();
    }

    /*
     * @PreUpdate
     * Función: Se utiliza para ejecutar un método justo antes de que una entidad
     * sea actualizada en la base de datos.
     * Uso: Este método puede ser utilizado para actualizar valores, realizar
     * validaciones, o registrar cambios antes de que la entidad se actualice.
     */
    @PreUpdate
    public void preUpdate() {
        System.out.println("Evento del ciclo de vida del objeto entity pre-update");
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
