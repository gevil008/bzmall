package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
public class BzAdmin implements Serializable, UserDetails{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @TableLogic(
            value = "0",
            delval = "1"
    )
    private Integer status;

    /**
     * 封装角色信息和权限信息 必须是GrantedAuthority 接口的实现类
     *
     * 官方给定的实现类是：SimpleGrantedAuthority
     */
    @TableField(exist = false)
    private Set<SimpleGrantedAuthority> authorities;

    public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public BzAdmin() {
    }

    public BzAdmin(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    /**
     * 封装授权信息
     */
    @Override
    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 账户不失效
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户不锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证信息失效
     * Credential 凭证信息 密码
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户可用性
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
