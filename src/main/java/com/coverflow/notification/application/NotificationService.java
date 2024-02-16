package com.coverflow.notification.application;

import com.coverflow.notification.domain.Notification;
import com.coverflow.notification.dto.response.FindNotificationResponse;
import com.coverflow.notification.exception.NotificationException;
import com.coverflow.notification.infrastructure.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * [알림 조회 메서드]
     */
    public List<FindNotificationResponse> findNotification(String memberId) {
        final List<Notification> notifications = notificationRepository.findByMemberId(UUID.fromString(memberId))
                .orElseThrow(() -> new NotificationException.NotificationNotFoundException(memberId));
        final List<FindNotificationResponse> findNotifications = new ArrayList<>();

        for (int i = 0; i < notifications.size(); i++) {
            findNotifications.add(i, FindNotificationResponse.from(notifications.get(i)));
        }
        return findNotifications;
    }
}
