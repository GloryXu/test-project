package com.redsun.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.RequestHandledEvent;

@Service
@Slf4j
public class MyPubisher implements ApplicationContextAware,ApplicationEventPublisher {

    private ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * Notify all <strong>matching</strong> listeners registered with this
     * application of an application event. Events may be framework events
     * (such as RequestHandledEvent) or application-specific events.
     *
     * @param event the event to publish
     * @see RequestHandledEvent
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        log.info("publishEvent ApplicationEvent event, into My Publisher's method");
        applicationContext.publishEvent(event);
    }

    /**
     * Notify all <strong>matching</strong> listeners registered with this
     * application of an event.
     * <p>If the specified {@code event} is not an {@link ApplicationEvent},
     * it is wrapped in a {@link PayloadApplicationEvent}.
     *
     * @param event the event to publish
     * @see PayloadApplicationEvent
     * @since 4.2
     */
    @Override
    public void publishEvent(Object event) {
        log.info("publishEvent Object event, into My Publisher's method");
        applicationContext.publishEvent(event);
    }
}
