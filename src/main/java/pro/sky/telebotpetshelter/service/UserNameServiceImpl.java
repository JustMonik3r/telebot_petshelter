package pro.sky.telebotpetshelter.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.telebotpetshelter.entity.UserName;
import pro.sky.telebotpetshelter.exceptions.NotFoundException;
import pro.sky.telebotpetshelter.repository.UserNameRepository;
import pro.sky.telebotpetshelter.utils.ModelUtil;

import java.util.List;
import java.util.Optional;
@Service
public class UserNameServiceImpl implements UserNameService {
    private final UserNameRepository userNameRepository;
    private static final String EXCEPTION_NOT_FOUND_USER = "Пользователь не найден!";

    public UserNameServiceImpl(UserNameRepository userNameRepository) {
        this.userNameRepository = userNameRepository;
    }

    @Override
    public UserName create(UserName user) {
        return userNameRepository.save(user);
    }

    @Override
    public UserName getById(Long id) {
        Optional<UserName> optionalUser = userNameRepository.findByTelegramId(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(EXCEPTION_NOT_FOUND_USER);
        }
        return optionalUser.get();
    }

    @Override
    public List<UserName> getAll() {
        return userNameRepository.findAll();
    }

    @Override
    public UserName update(UserName user) {
        UserName currentUser = getById(user.getTelegramId());
        ModelUtil.copyNonNullFields(user, currentUser);
        return userNameRepository.save(currentUser);
    }

    @Override
    public void deleteUser(Long id) {
        userNameRepository.deleteById(id);
    }
    /**
     * The method is registers the user to the DB to designate the old user.
     * It creates the object of {@link UserName} and sets all the fields.
     * <br>
     * {@link UserNameRepository} saves User to the DB.
     *
     * @param update
     */
    @Override
    public void registerUser(Update update) {
        Long chatId = update.message().chat().id();
        UserName user = new UserName();
        user.setTelegramId(chatId);
        user.setFirstName(update.message().chat().firstName());
        userNameRepository.save(user);
    }

    @Override
    public boolean newUser(Update update) {
        return !(userNameRepository.existsById(update.message().chat().id()));
    }
}
